package cn.heshiqian.framework.h.cflog.core;

import cn.heshiqian.framework.h.cflog.exception.CFLogException;
import cn.heshiqian.framework.h.cflog.observer.CFLogObserver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.*;

public final class AsynSaveLog implements CFLogObserver {

    private static AsynSaveLog asynSaveLog;
    private static Thread thread;

    private static ArrayList<String> temp = new ArrayList<>();

    private AsynSaveLog() {
    }

    public static AsynSaveLog instance() {
        if (asynSaveLog == null){
            asynSaveLog = new AsynSaveLog();
            thread=new Writer();
            thread.start();
        }
        return asynSaveLog;
    }

    private ArrayList<String> getTemp(){
        return temp;
    }

    @Override
    public synchronized void update(String msg) {
        synchronized (temp){
            temp.add(msg);
        }
        synchronized (thread){
            thread.notify();
        }
    }

    private static class Writer extends Thread {
        private static File Logfile;
        private static BufferedWriter fileWriter;
        private void writeLog(String msg) {
            if (Logfile == null) {
                Logfile = newLogFile();
            }
            if (Logfile.length() >= 10 * 1024 * 1024) {
                Logfile = newLogFile();
                try {
                    fileWriter.close();
                    fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Logfile, true), "UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter == null) {
                try {
                    fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Logfile, true), "UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            openFileAWriteIn(msg);
        }

        private File newLogFile() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            File file = new File(simpleDateFormat.format(new Date()) + ".log");
            boolean isWritable = file.canWrite();
            if(!isWritable){
                boolean writable = file.setWritable(true);
                boolean readable = file.setReadable(true);
                if(!writable||!readable){
                    setFilePermissionByNIO(file);
                }
            }
            return file;
        }

        private void setFilePermissionByNIO(File file) {
            Set<PosixFilePermission> parma=new HashSet<>();
            parma.add(PosixFilePermission.OWNER_READ);
            parma.add(PosixFilePermission.OWNER_WRITE);
            parma.add(PosixFilePermission.OWNER_EXECUTE);
            parma.add(PosixFilePermission.GROUP_READ);
            parma.add(PosixFilePermission.GROUP_WRITE);
            parma.add(PosixFilePermission.GROUP_EXECUTE);
            Path path = Paths.get(file.getAbsolutePath());
            try {
                Files.setPosixFilePermissions(path,parma);
            }catch (UnsupportedOperationException e){
                System.out.println("NIO运行在Windows下");
            }catch (IOException e) {
                throw new CFLogException("NIO设置文件权限失败",e);
            }
        }

        private void openFileAWriteIn(String msg) {
            try {
                String data = new String((msg + "\r\n").getBytes("UTF-8"), "UTF-8");
                fileWriter.write(data);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true){
                ArrayList<String> temp = asynSaveLog.getTemp();
                synchronized (temp){
                    if(!temp.isEmpty()){
                        for(String s:temp){
                            writeLog(s);
                        }
                        temp.clear();
                    }
                }
                try {
                    synchronized (thread){
                        thread.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
