package com.jun.sail.myservice;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import javassist.ClassPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

@SpringBootApplication
public class MyServiceApplication {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        // 获取当前虚拟机的pid
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        VirtualMachine vm = VirtualMachine.attach(pid);
        // 判断Jar文件是否存在　
        String pathname = "/Users/wangjun/work/code/github/springboot-example/java-agent/my-java-agent/target/my-java-agent-1.0-SNAPSHOT.jar";
        if (new File(pathname).exists()) {
            vm.loadAgent(pathname);
        }
        SpringApplication.run(MyServiceApplication.class, args);
    }

}
