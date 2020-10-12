package com.jun.sail.agent;

import javassist.ClassPool;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyClassFieTransform implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // 定义重新编译之后的字符流数组
        byte[] newClassFileBuffer = classfileBuffer;

        // 重定义指定类，也可以重定义指定package下的类，使用者自由发挥
        String transClassName = "com/jun/sail/myservice/service/HelloService";
        if (className.equals(transClassName)) {
            System.out.println("监控到目标类,重新编辑Class文件字符流...");
            // TODO 对目标类的Class文件字节流进行重新编辑
            // 对byte[]重新编译可以使用第三方工具如javassist,感兴趣的可自行研究
            // 本文图方便，直接返回旧的字节数组
            newClassFileBuffer = classfileBuffer;
        }
        return newClassFileBuffer;
    }
}