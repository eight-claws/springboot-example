package com.jun.cloud.service;

import com.jun.cloud.service.common.enums.SubTableEnum;
import com.jun.cloud.service.service.impl.SubTableServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试分表
 *
 * @author Jun
 * 创建时间： 2019/7/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class SubTableServiceTest {


    @Autowired
    private SubTableServiceImpl subTableService;

    @Test
    public void testSubTableForWeek() {
        subTableService.subTableForWeek(SubTableEnum.TB_REGION);
    }

}
