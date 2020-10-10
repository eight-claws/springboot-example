package com.jun.sail.myservice;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

@SpringBootApplication
public class MyServiceApplication {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        String pid= ManagementFactory.getRuntimeMXBean().getName().split("@")[0];//获取当前虚拟机的pid
        VirtualMachine vm = VirtualMachine.attach(pid);//首先判断Jar文件是否存在
        if(new File("E:/code/github/springboot-example/java-agent/my-java-agent/target/my-java-agent-1.0-SNAPSHOT.jar").exists()){
            vm.loadAgent("E:/code/github/springboot-example/java-agent/my-java-agent/target/my-java-agent-1.0-SNAPSHOT.jar");
        }
        SpringApplication.run(MyServiceApplication.class, args);
    }

}
