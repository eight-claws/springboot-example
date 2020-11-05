package com.jun.sail.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author wangjun
 * @Date 2020/11/5
 **/
@Data
@AllArgsConstructor
public class LocalDateTimeDto {
    private LocalDate day;
    private LocalDateTime dateTime;
    private LocalTime time;

}
