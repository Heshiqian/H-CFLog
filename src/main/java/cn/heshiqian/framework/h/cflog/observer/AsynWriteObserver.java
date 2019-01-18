package cn.heshiqian.framework.h.cflog.observer;

public interface AsynWriteObserver {

    public void registerObserver(CFLogObserver observer);
    public void removeObserver(CFLogObserver observer);
    public void notifyObserver();

}
