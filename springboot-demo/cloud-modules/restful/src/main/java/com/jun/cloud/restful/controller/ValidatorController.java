package com.jun.cloud.restful.controller;

import com.jun.cloud.restful.dto.CountGroupDto;
import com.jun.cloud.restful.dto.UserDto;
import com.jun.sail.sailvalidate.group.Save;
import com.jun.sail.sailvalidate.group.Update;
import com.jun.sail.utils.commons.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;


/**
 * 参数校验
 *
 * @author 94977
 * @create 2019/1/1
 */
@RestController
@RequestMapping("/validate")
@Api(tags = "参数校验validate")
@Validated
public class ValidatorController {


    @GetMapping(value = "/query/{id}")
    public BaseResponse<CountGroupDto> getCountGroup(@PathVariable(name = "id") String id,
                                                     @RequestParam("name") String name) {
        BaseResponse<CountGroupDto> response = new BaseResponse<>();
        CountGroupDto countGroupDto = new CountGroupDto();
        countGroupDto.setId(id);
        countGroupDto.setName(name);
        response.setData(countGroupDto);
        return null;
    }


    /**
     * 这里的@Validated({Save.class, Default.class}) 其中Default.class是校验注解默认的分组,
     * （也就说明自定义校验注解时要加上）
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "添加用户")
    public BaseResponse addUser(@Validated({Save.class, Default.class}) @RequestBody UserDto addDto) {
        BaseResponse<String> response = new BaseResponse<>();
        response.setMsg("添加成功");
        return response;
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "修改用户")
    public String updatedUser(@Validated({Update.class, Default.class}) @RequestBody UserDto updateDto) {
        return "修改成功";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyyMMdd"), true);
        binder.registerCustomEditor(Data.class, editor);
    }

}
