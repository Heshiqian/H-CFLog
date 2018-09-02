package cn.heshiqian.framework.h.cflog.generator;

import org.fusesource.jansi.Ansi;

import java.text.SimpleDateFormat;

public final class Generator {

    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.SSS]");

    private Generator(){ }


    public static String _err(String msg,String className){
        return  "[Error] "+simpleDateFormat.format(System.currentTimeMillis())+" ["+className+"] --> "+ msg;
    }
    public static String _info(String msg,String className){
        return "[Info] "+simpleDateFormat.format(System.currentTimeMillis())+" ["+className+"] --> "+ msg;
    }
    public static String _war(String msg,String className){
        return "[Waring] "+simpleDateFormat.format(System.currentTimeMillis())+" ["+className+"] --> "+ msg;
    }
    public static String _o(String msg,String className){
        return "[All] "+simpleDateFormat.format(System.currentTimeMillis())+" ["+className+"] --> "+ msg;
    }
}
