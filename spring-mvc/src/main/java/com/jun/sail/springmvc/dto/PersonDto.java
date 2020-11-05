package com.jun.sail.springmvc.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.jun.sail.springmvc.constant.enums.DepartmentEnum;
import com.jun.sail.springmvc.constant.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

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

    private SexEnum sex;

    private DepartmentEnum department;

    private LocalDate birthday;

    @NumberFormat(style = NumberFormat.Style.NUMBER,pattern = "#,##.00")
    private double height;

    /**
     * spring-mvc已经支持LocalDateTime的序列化，默认为2020-11-04T14:29:11.686，2020-11-04T14:29:11也可以反序列化
     */
    private LocalDateTime signTime;

    public PersonDto() {
    }
}
