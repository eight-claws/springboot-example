package com.jun.cloud.restful.controller;

import com.jun.cloud.restful.dto.CameraDto;
import com.jun.cloud.restful.dto.CountGroupDto;
import com.jun.cloud.restful.service.impl.TestServiceImpl;
import com.jun.sail.sailcsv.OpenCsvUtil;
import com.jun.sail.utils.commons.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Jun
 * 创建时间： 2019/9/28
 */
@Api(tags = "用户API")
@RestController
@RequestMapping("/person")
@Slf4j
public class UserController {

    @Autowired
    private TestServiceImpl testService;

    @GetMapping("/testLog")
    @ApiOperation(value = "用来触发请求，测试logger级别")
    public BaseResponse testlogger( ) {
        BaseResponse<String> response = new BaseResponse<>();
        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");
        testService.testUrl(new CameraDto("111", 343434));
        response.setData("成功");
        return response;
    }


    /**
     * 导出csv文件
     */
    @GetMapping("/export")
    @ApiOperation(value = "导出人员列表csv文件")
    public void exportUser(HttpServletResponse response){
        String csvName = "userConsumeRecord";
        //从数据库获取countGroupDtoList集合
        List<CountGroupDto> countGroupDtoList = geCountGroupDtoList();
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("name", "姓名");
        fieldMap.put("type", "类型");
        fieldMap.put("regionCode", "所属区域");
        OpenCsvUtil.writeCsvFile(csvName, countGroupDtoList, fieldMap, response);
    }


    private List<CountGroupDto> geCountGroupDtoList(){
        List<CountGroupDto> countGroupDtoList = new ArrayList<>();
        CountGroupDto countGroupDto;
        for (int i = 0; i < 10; i++) {
            countGroupDto = new CountGroupDto();
            countGroupDto.setName("小于" + i);
            countGroupDto.setType("金额模式");
            countGroupDto.setRegionCode("浙江");
            countGroupDtoList.add(countGroupDto);
        }
        return countGroupDtoList;
    }

}


