package com.jun.sail.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTransformer implements ClassFileTransformer {

    final static String prefix = "\nlong startTime = System.currentTimeMillis();\n";
    final static String postfix = "\nlong endTime = System.currentTimeMillis();\n";

    // 被处理的方法列表
    final static Map<String, List<String>> methodMap = new HashMap<>();

    public MyTransformer() {
        add("com.jun.sail.myservice.service.HelloService.say");
        add("com.jun.sail.myservice.service.HelloService.say2");
    }

    private void add(String methodString) {
        String className = methodString.substring(0, methodString.lastIndexOf("."));
        String methodName = methodString.substring(methodString.lastIndexOf(".") + 1);
        List<String> list = methodMap.computeIfAbsent(className, k -> new ArrayList<>());
        list.add(methodName);
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        className = className.replace("/", ".");
        if (methodMap.containsKey(className)) { // 判断加载的class的包路径是不是需要监控的类
            CtClass ctclass = null;
            try {
                // 使用全称,用于取得字节码类<使用javassist>
                ctclass = ClassPool.getDefault().get(className);
                for (String methodName : methodMap.get(className)) {
                    String outputStr = "\nSystem.out.println(\"this method [" + methodName
                            + "] cost:\" +(endTime - startTime) +\"ms.\");";
                    // 得到这方法实例
                    CtMethod ctmethod = ctclass.getDeclaredMethod(methodName);

                    // 根据原来的方法 创建新的方法，名字为原来的methodName
                    CtMethod newMethod = CtNewMethod.copy(ctmethod, methodName, ctclass, null);

                    // 把旧方法名字改掉，否则会冲突
                    String oldMethodName = methodName + "$old";
                    ctmethod.setName(oldMethodName);

                    // 构建新的方法体
                    StringBuilder bodyStr = new StringBuilder();
                    bodyStr.append("{");
                    bodyStr.append(prefix);
                    bodyStr.append(oldMethodName).append("($$);\n");// 调用原有代码，类似于method();($$)表示所有的参数
                    bodyStr.append(postfix);
                    bodyStr.append(outputStr);
                    bodyStr.append("}");
                    newMethod.setBody(bodyStr.toString());

                    newMethod.setName(methodName);
                    ctclass.addMethod(newMethod);
                }
                return ctclass.toBytecode();
            } catch (Exception e) {
                System.out.println("AAAAA" + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}