package cn.heshiqian.framework.h.cflog.core;

import cn.heshiqian.framework.h.cflog.exception.CFLogException;
import cn.heshiqian.framework.h.cflog.generator.Generator;
import org.fusesource.jansi.Ansi;

public final class CFLog {

    private Class logClass;
    private String handleClassName="UnknownClass";

    private CFLog(){

    }
    public CFLog(Class c){
        if(c==null){
            throw new CFLogException("Null Class");
        }
        logClass=c;
        handleClassName=logClass.getSimpleName();
    }
    public void err(String msg){
        System.out.println(Generator._err(msg,handleClassName));
    }
    public void info(String msg){
        System.out.println(Generator._info(msg,handleClassName));
    }
    public void war(String msg){
        System.out.println(Generator._war(msg,handleClassName));
    }
    public void print(String msg){
        System.out.println(Generator._o(msg,handleClassName));
    }
}
