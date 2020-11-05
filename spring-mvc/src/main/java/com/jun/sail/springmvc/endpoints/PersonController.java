package com.jun.sail.springmvc.endpoints;

import com.jun.sail.springmvc.constant.enums.DepartmentEnum;
import com.jun.sail.springmvc.constant.enums.SexEnum;
import com.jun.sail.springmvc.dto.PersonDto;
import com.jun.sail.springmvc.web.returnvalue.SailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ConfigurationProperties(prefix = "person")
@RestController
@RequestMapping("/person")
public class PersonController {

    private String name;

    private Integer age;

    @Value("${person.birthday:8}")
    private String birthday;

    /**
     * 此处演示@Value引用的值不存在时指定默认值
     */
    @Value("${person.height:2}")
    private Integer height;

    @SailResponse("查询成功")
    @GetMapping("/get/{sex}")
    public PersonDto getPerson(@PathVariable("sex") SexEnum sex, @RequestParam DepartmentEnum departmentEnum) {
        return new PersonDto(name, sex, departmentEnum, LocalDate.now(), height, LocalDateTime.now());
    }

    @SailResponse("查询成功")
    @GetMapping("/query")
    public PersonDto getPersons(PersonDto personDto) {
        return personDto;
    }

    @SailResponse("保存成功")
    @PostMapping("/save")
    public PersonDto savePerson(@RequestBody PersonDto personDto) {
        return personDto;
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
