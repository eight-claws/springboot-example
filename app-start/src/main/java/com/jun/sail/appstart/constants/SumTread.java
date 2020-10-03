package com.jun.sail.appstart.constants;


import java.util.concurrent.Callable;


public class SumTread implements Callable<Long> {
    private long start;
    private long end;

    public SumTread(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 返回[start,end)的和
     */
    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (long i = start; i < end; i++) {
            sum += i;
        }
        return sum;
    }
}
