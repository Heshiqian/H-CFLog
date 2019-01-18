package cn.heshiqian.framework.h.cflog.observer;

import java.util.ArrayList;
import java.util.List;

public class CFLogWatchServer implements AsynWriteObserver {
    private static CFLogWatchServer cfLogWatchServer;
    private static List<CFLogObserver> observers;
    private static String message;

    private CFLogWatchServer() {

    }

    public static CFLogWatchServer instance(){
        if(cfLogWatchServer==null){
            observers = new ArrayList<>();
            cfLogWatchServer=new CFLogWatchServer();
        }
        return cfLogWatchServer;
    }

    public static void send(String msg){
        message=msg;
        cfLogWatchServer.notifyObserver();
    }

    @Override
    public void registerObserver(CFLogObserver observer) {
        if (observers != null)
            observers.add(observer);
    }

    @Override
    public void removeObserver(CFLogObserver observer) {
        if(!observers.isEmpty())
            observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(CFLogObserver observer:observers){
            observer.update(message);
        }
    }

    public void sendMessage(String msg){
        message=msg;
        notifyObserver();
    }
}
