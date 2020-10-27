package com.jun.sail.feature.actuate;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;


public class AppTestCaseHealthIndicator extends AbstractHealthIndicator {


    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("result", "132ms");
    }
}