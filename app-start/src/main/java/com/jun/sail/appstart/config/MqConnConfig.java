package com.jun.sail.appstart.config;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * SmartLifecycle的实现类，在spring容器初始化完毕和关闭的时候被spring容器回调，完成特定的业务需求
 *
 * @author Jun
 * 创建时间： 2020/5/16
 */
@Slf4j
@Component
public class MqConnConfig implements SmartLifecycle {

    private boolean running = false;


    public void setRunning(boolean running) {
        this.running = running;
    }


    @Override
    public void start() {
        log.info(AppStartConstant.LOG_SPARATOR + "[ SmartLifecycle ]");
        setRunning(true);

    }

    @Override
    public void stop() {
        log.info(AppStartConstant.LOG_SPARATOR + "app stop, SmartLifecycle-stop() is invoked");
        setRunning(false);
    }

    @Override
    public void stop(Runnable callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info(AppStartConstant.LOG_SPARATOR + "app stop, SmartLifecycle-start(Runnable callback) is invoked");

                //设置为false，表示已经不在执行中了
                setRunning(false);

                //callback中有个CountDownLatch实例，总数是SmartLifecycle对象的数量，
                //此方法被回调时CountDownLatch实例才会减一，初始化容器的线程一直在wait中；
                callback.run();
            }
        }).start();
    }


    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public boolean isAutoStartup() {
        //只有设置为true，start方法才会被回调
        return true;
    }


    @Override
    public int getPhase() {
        return 0;
    }
}
