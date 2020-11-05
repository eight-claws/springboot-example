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
 * Formatter和Converter的差别是，Converter不同类型之间互相转换，而Formatter只能负责字符串和对象互相转换。
 * 所以字符串和对象之间转换时，Formatter 和 Converter 的功能是一样的，从语义上说Formatter更好些，而且在转换时间，货币，数字时，可以获取Local属性
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
