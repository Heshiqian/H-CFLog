package cn.heshiqian.framework.h.cflog.core;

import cn.heshiqian.framework.h.cflog.generator.Generator;
import org.fusesource.jansi.Ansi;

public final class Logger {
    String handleClassName;
    public Logger(String handleClassName){
        this.handleClassName=handleClassName;
    }
    public void err(String msg){
        String err = Generator._err(msg, handleClassName);
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a(err).reset().toString());
        AsynSaveLog.joinLogQueue(err);
    }
    public void info(String msg){
        String info = Generator._info(msg, handleClassName);
        System.out.println(Ansi.ansi().fg(Ansi.Color.BLUE).a(info).reset().toString());
        AsynSaveLog.joinLogQueue(info);
    }
    public void war(String msg){
        String war = Generator._war(msg, handleClassName);
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a(war).reset().toString());
        AsynSaveLog.joinLogQueue(war);
    }
    public void print(String msg){
        String o = Generator._o(msg, handleClassName);
        System.out.println(o);
        AsynSaveLog.joinLogQueue(o);
    }
}
