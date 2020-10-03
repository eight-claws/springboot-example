package com.jun.cloud.restful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jun
 * 创建时间： 2019/5/12
 */
@Data
@AllArgsConstructor
public class PersonDto {

    private String name;

    private Integer age;

    private String birthday;

    private Integer height;

    public PersonDto(String name, Integer age, Integer height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }


}
