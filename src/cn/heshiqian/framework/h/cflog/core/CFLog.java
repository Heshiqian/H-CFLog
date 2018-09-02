package cn.heshiqian.framework.h.cflog.core;

import cn.heshiqian.framework.h.cflog.exception.CFLogException;

public final class CFLog {

    private static String handleClassName="";

    private CFLog(){ }

    public static Logger logger(){
        return new Logger(handleClassName);
    }
    public static Logger logger(Class c){
        if(c==null)
            throw new CFLogException("Null Class");
        handleClassName=c.getSimpleName();
        return new Logger(handleClassName);
    }


}
