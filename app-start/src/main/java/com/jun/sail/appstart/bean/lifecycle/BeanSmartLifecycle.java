package com.jun.sail.appstart.bean.lifecycle;

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
public class BeanSmartLifecycle implements SmartLifecycle {

    private boolean running = false;

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void start() {
        log.info(AppStartConstant.LOG_SEPARATOR_BEAN + "[ Lifecycle ]");
        setRunning(true);
    }

    @Override
    public void stop() {
        log.info(AppStartConstant.LOG_SEPARATOR_BEAN + "app stop, SmartLifecycle-stop() is invoked");
        setRunning(false);
    }

    @Override
    public boolean isRunning() {
        return running;
    }


    /**
     * 上面是Lifecycle规定的接口
     * <p>
     * ================================================
     * <p>
     * 下面是SmartLifecycle规定的接口
     */
    @Override
    public boolean isAutoStartup() {
        //只有设置为true，start方法才会被回调
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        new Thread(() -> {
            log.info(AppStartConstant.LOG_SEPARATOR_BEAN + "app stop, SmartLifecycle-start(Runnable callback) is invoked");

            //设置为false，表示已经不在执行中了
            setRunning(false);

            //callback中有个CountDownLatch实例，总数是SmartLifecycle对象的数量，
            //此方法被回调时CountDownLatch实例才会减一，初始化容器的线程一直在wait中；
            callback.run();
        }).start();
    }

    /**
     * Phased用于控制多个bean的启动顺序，value较小的会先启动，shutdown时会后关闭。
     * 如ComponentB依赖componentA先启动，则componentA.phase()应该返回一个较小的值，关闭时B会先关闭。
     * 如果明确指定depends-on，以depends-on为准。
     * 默认返回Integer.MAX_VALUE，也就是after其他容器bean
     *
     * @return
     */
    @Override
    public int getPhase() {
        return 0;
    }
}
