package com.jun.sail.myjavaattach;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 启动时在program arguments填入--pid=19816，即监控目标jvm pid，或者手动替换pid也可
 * attach到目标jvm后，通过load把自己写的agent放进去。主要的逻辑还是在agent里
 */
@Component
public class AppStartListener implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String pid = args.getOptionValues("pid").get(0);
        System.out.println("pid= " + pid);

        VirtualMachineDescriptor virtualMachineDescriptor = null;
        for (VirtualMachineDescriptor descriptor : VirtualMachine.list()) {
            if (pid.equals(descriptor.id())) {
                virtualMachineDescriptor = descriptor;
            } else {
                VirtualMachine.attach(pid);
            }
        }

        VirtualMachine vm = null;
        try {
            if (null != virtualMachineDescriptor) {
                vm = VirtualMachine.attach(virtualMachineDescriptor);
            } else {
                vm = VirtualMachine.attach(pid);
            }

            String targetJavaVersion = vm.getSystemProperties().getProperty("java.specification.version");
            System.out.println("targetJavaVersion is " + targetJavaVersion);

            // 判断Jar文件是否存在　
            String pathname = "E:\\code\\github\\springboot-example\\java-agent\\my-java-agent\\target\\my-java-agent-1.0-SNAPSHOT.jar";
            if (new File(pathname).exists()) {
                vm.loadAgent(pathname);
            }
        } catch (Exception e) {
            if (null != vm) {
                vm.detach();
            }
        }
    }
}
