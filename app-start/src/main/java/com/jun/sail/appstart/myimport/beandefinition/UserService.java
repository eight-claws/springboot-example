package com.jun.sail.appstart.myimport.beandefinition;

import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Override
    @MyAsync
    public void testAdd() {
        System.out.println("testAdd async exec, " + Thread.currentThread().getName());
    }

    @Override
    public void testGet() {
        System.out.println("testGet sync exec, " + Thread.currentThread().getName());
    }
}
