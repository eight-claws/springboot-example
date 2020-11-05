package com.jun.sail.springmvc.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 用于转换RequestParam和PathVariable参数。
 * @Author wangjun
 * @Date 2020/11/5
 **/
public class LocalDateTimeConverter {

    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_HH_mm_ss = "HH:mm:ss";

    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

    /**
     * LocalDate转换器，用于转换RequestParam和PathVariable参数
     */
    public static Converter<String, LocalDate> localDateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                if (!ObjectUtils.isEmpty(source)) {
                    return LocalDate.parse(source, DateTimeFormatter.ofPattern(DATE_FORMAT_yyyy_MM_dd));
                }
                return null;

            }
        };
    }

    /**
     * LocalDateTime转换器，用于转换RequestParam和PathVariable参数
     */
    public static Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                if (!ObjectUtils.isEmpty(source)) {
                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DATE_FORMAT_yyyy_MM_dd_HH_mm_ss));
                }
                return null;
            }
        };
    }

    /**
     * LocalTime转换器，用于转换RequestParam和PathVariable参数
     */
    public static Converter<String, LocalTime> localTimeConverter() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                if (!ObjectUtils.isEmpty(source)) {
                    return LocalTime.parse(source, DateTimeFormatter.ofPattern(DATE_FORMAT_HH_mm_ss));
                }
                return null;
            }
        };
    }


}
