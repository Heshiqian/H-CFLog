import cn.heshiqian.framework.h.cflog.core.CFLog;
import cn.heshiqian.framework.h.cflog.core.Logger;

public class Test {


    @org.junit.Test
    public void t1(){
        Logger logger = CFLog.logger(this.getClass());
        logger.print("aaaa");
    }
}
