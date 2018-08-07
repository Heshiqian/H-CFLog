package cn.heshiqian.framework.h.cflog.generator;

import org.fusesource.jansi.Ansi;

import java.text.SimpleDateFormat;

public final class Generator {

    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");

    private Generator(){ }


    public static String _err(String msg,String className){
        return Ansi.ansi().fg(Ansi.Color.RED).a("[Error] "+simpleDateFormat.format(System.currentTimeMillis())+" ["+className+"] --> "+ msg).reset().toString();
    }
    public static String _info(String msg,String className){
        return Ansi.ansi().fg(Ansi.Color.BLUE).a("[Info] "+simpleDateFormat.format(System.currentTimeMillis())+" ["+className+"] --> "+ msg).reset().toString();
    }
    public static String _war(String msg,String className){
        return Ansi.ansi().fg(Ansi.Color.CYAN).a("[Waring] "+simpleDateFormat.format(System.currentTimeMillis())+" ["+className+"] --> "+ msg).reset().toString();
    }
    public static String _o(String msg,String className){
        return Ansi.ansi().fg(Ansi.Color.WHITE).a("[All] "+simpleDateFormat.format(System.currentTimeMillis())+" ["+className+"] --> "+ msg).reset().toString();
    }
}
