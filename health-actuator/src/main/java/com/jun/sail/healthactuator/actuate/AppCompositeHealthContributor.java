package com.jun.sail.healthactuator.actuate;

import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.NamedContributor;

import java.util.Iterator;


// the name will be displayed in /health endpoint
//@Component("app")
public class AppCompositeHealthContributor implements CompositeHealthContributor {


    @Override
    public HealthContributor getContributor(String name) {
        return new AppTestCaseHealthIndicator();
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