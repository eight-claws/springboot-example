package com.jun.sail.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Jun
 * 创建时间： 2019/5/12
 */
@Data
@AllArgsConstructor
public class PersonDto {

    private String name;

    private Integer age;

    private LocalDate birthday;

    private Integer height;

    private LocalDateTime signTime;

    public PersonDto() {
    }
}
