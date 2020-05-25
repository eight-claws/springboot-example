package com.jun.sail.healthactuator.actuate;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;


public class E2ETestCaseHealthIndicator extends AbstractHealthIndicator {


    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
       builder.up().withDetail("result", "不知道啊");
    }
}