package com.jun.sail.springbootsecurit.endpoint.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class InMemoryQQDatabase {

    public static Map<String, QQAccount> database;

    static {
        database = new HashMap<>();
        database.put("u1", QQAccount.builder().qq("u1").nickName("鱼非渔").level("54").build());
        database.put("u2", QQAccount.builder().qq("u2").nickName("下一秒升华").level("31").build());

        QQAccount qqAccount1 = database.get("u1");
        qqAccount1.setFans(new ArrayList<>());
        for (int i = 0; i < 5; i++) {
            qqAccount1.getFans().add(QQAccount.builder().qq("1000000" + i).nickName("fan" + i).level(i + "").build());
        }

        QQAccount qqAccount2 = database.get("u2");
        qqAccount2.setFans(new ArrayList<>());
        for (int i = 0; i < 3; i++) {
            qqAccount2.getFans().add(QQAccount.builder().qq("2000000" + i).nickName("fan" + i).level(i + "").build());
        }
    }

}