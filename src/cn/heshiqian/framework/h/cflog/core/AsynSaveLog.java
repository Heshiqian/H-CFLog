package cn.heshiqian.framework.h.cflog.core;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class AsynSaveLog {

    private static AsynSaveLog asynSaveLog;
    private static File Logfile;
    private static BufferedWriter fileWriter;

    private AsynSaveLog() {
    }

    public static void joinLogQueue(String msg) {

        if (asynSaveLog == null)
            asynSaveLog = new AsynSaveLog();

        asynSaveLog.writeLog(msg);

    }

    private void writeLog(String msg) {

        if (Logfile == null) {
            Logfile = newLogFile();
        }

        if (Logfile.length() >= 10 * 1024 * 1024) {
            Logfile = newLogFile();
            try {
                fileWriter.close();
                fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Logfile,true),"UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileWriter == null) {
            try {
                fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Logfile,true),"UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        openFileAWriteIn(msg);

    }

    private File newLogFile() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return new File(simpleDateFormat.format(new Date()) + ".log");
    }

    private void openFileAWriteIn(String msg) {
        try {
            String data=new String((msg+"\r\n").getBytes("UTF-8"),"UTF-8");
            fileWriter.write(data);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
