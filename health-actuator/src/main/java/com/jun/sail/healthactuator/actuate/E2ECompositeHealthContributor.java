package com.jun.sail.healthactuator.actuate;

import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.NamedContributor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


// the name will be displayed in /health endpoint
//@Component("e2e")
public class E2ECompositeHealthContributor implements CompositeHealthContributor {


    @Override
    public HealthContributor getContributor(String name) {
        return new E2ETestCaseHealthIndicator();
    }

    @Override
    public Iterator<NamedContributor<HealthContributor>> iterator() {
        return null;
    }

    //
    //@Override
    //public Iterator<NamedContributor<HealthContributor>> iterator() {
    //
    //
    //    return new MyHealthIndicator();
    //}
}