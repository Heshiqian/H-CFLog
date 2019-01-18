package cn.heshiqian.framework.h.cflog.core;

import cn.heshiqian.framework.h.cflog.exception.CFLogException;
import cn.heshiqian.framework.h.cflog.observer.CFLogWatchServer;

public final class CFLog {

    private static AsynSaveLog instance;
    private static CFLogWatchServer watchServer;

    private CFLog() {
    }

    public synchronized static Logger logger() {
        return logger(null);
    }

    public synchronized static Logger logger(Class c) {
        if (instance == null)
            instance = AsynSaveLog.instance();
        if(watchServer==null){
            watchServer=CFLogWatchServer.instance();
            watchServer.registerObserver(instance);
        }
        if (c == null)
            throw new CFLogException("Null Class");
        return new Logger(c.getSimpleName());
    }


}
