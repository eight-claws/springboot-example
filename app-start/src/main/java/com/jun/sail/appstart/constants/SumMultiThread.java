package com.jun.sail.appstart.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SumMultiThread {

    public static void main(String[] args) {
        int n = 100000;
        int numThread = 3;
        ExecutorService threadPool = Executors.newFixedThreadPool(numThread);

        long start = System.currentTimeMillis();

        List<Future<Long>> list = new ArrayList<>();
        for (int i = 0; i < numThread; i++) {
            Future<Long> longFuture = threadPool.submit(new SumTread(i * n / numThread, (i + 1) * n / numThread));
            list.add(longFuture);
        }
        threadPool.shutdown();

        long result = 0;
        for (Future<Long> future : list) {
            try {
                result += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 单独处理n，包括n
        result += n;
        System.out.println("sum result= " + result + ", spend ms: " + (System.currentTimeMillis() - start));
    }
}
