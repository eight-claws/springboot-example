package com.jun.cloud.restful.controller;

import com.jun.cloud.restful.dto.PersonDto;
import com.jun.sail.utils.commons.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ConfigurationProperties(prefix = "person")
@RestController
@Api(tags = "自定义属性properties获取测试")
@RequestMapping("/person")
public class PropertiesController {

    private String name;

    private Integer age;

    @Value("${person.birthday}")
    private String birthday;

    /**
     * 此处演示@Value引用的值不存在时指定默认值
     */
    @Value("${person.height:2}")
    private Integer height;

    @GetMapping("/get")
    @ApiOperation(value = "测试自定义属性properties获取", notes = "返回person对象")
    public BaseResponse<PersonDto> getPerson() {
        BaseResponse<PersonDto> response = new BaseResponse<>();
        PersonDto personDto = new PersonDto(name, age, birthday, height);
        response.setData(personDto);
        return response;
    }


    /**
     * 注意：使用@ConfigurationProperties必须为属性提供set方法，而Value就不必。
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;

    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
