package com.jun.sail.springmvc.web;

import org.springframework.format.Formatter;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Formatter和Converter的差别是，Converter可以转换任意类型的对象，而Formatter只能负责string和T之间的转换
 *
 * @Author wangjun
 * @Date 2020/11/5
 **/
public class LocalDateTimeFormatter {

    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_HH_mm_ss = "HH:mm:ss";

    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

    /**
     * LocalDate的Formatter，用于转换RequestParam和PathVariable参数
     */
    public static Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @Override
            public String print(LocalDateTime object, Locale locale) {
                return DateTimeFormatter.ofPattern(DATE_FORMAT_yyyy_MM_dd_HH_mm_ss).format(object);
            }

            @Override
            public LocalDateTime parse(String text, Locale locale) throws ParseException {
                if (!ObjectUtils.isEmpty(text)) {
                    return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DATE_FORMAT_yyyy_MM_dd_HH_mm_ss));
                }
                return null;
            }
        };
    }

    /**
     * LocalDate的Formatter，用于转换RequestParam和PathVariable参数
     */
    public static Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public String print(LocalDate object, Locale locale) {
                return DateTimeFormatter.ofPattern(DATE_FORMAT_yyyy_MM_dd).format(object);
            }

            @Override
            public LocalDate parse(String text, Locale locale) throws ParseException {
                if (!ObjectUtils.isEmpty(text)) {
                    return LocalDate.parse(text, DateTimeFormatter.ofPattern(DATE_FORMAT_yyyy_MM_dd));
                }
                return null;
            }
        };
    }

    /**
     * LocalTime的Formatter，用于转换RequestParam和PathVariable参数
     */
    public static Formatter<LocalTime> localTimeFormatter() {
        return new Formatter<LocalTime>() {
            @Override
            public String print(LocalTime object, Locale locale) {
                return DateTimeFormatter.ofPattern(DATE_FORMAT_HH_mm_ss).format(object);
            }

            @Override
            public LocalTime parse(String text, Locale locale) throws ParseException {
                if (!ObjectUtils.isEmpty(text)) {
                    return LocalTime.parse(text, DateTimeFormatter.ofPattern(DATE_FORMAT_HH_mm_ss));
                }
                return null;
            }
        };
    }

}
