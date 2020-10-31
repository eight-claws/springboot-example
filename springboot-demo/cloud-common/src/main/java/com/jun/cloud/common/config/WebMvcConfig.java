package com.jun.cloud.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.jun.cloud.common.interceptor.InterceptorDemo;
import com.jun.sail.utils.datetime.DateTimeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * WebMvc配置：
 * 1.消息转换器的配置，提供Jackson的支持
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对外提供的api接口验证及追踪日志
        //registry.addInterceptor(new RestApiInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new InterceptorDemo()).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 注意这里使用的是extendMessageConverters，这个方法不会覆盖springmvc已默认添加的HttpMessageConverter
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(customDateObjectMapper());
        converters.add(messageConverter);
        converters.add(stringHttpMessageConverter());
    }

    //@Bean
    //public MappingJackson2HttpMessageConverter customerMappingJackson2HttpMessageConverter(){
    //    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
    //    messageConverter.setObjectMapper(customDateObjectMapper());
    //    return messageConverter;
    //}

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter stringConvert = new StringHttpMessageConverter();
        List<MediaType> stringMediaTypes = new ArrayList<MediaType>() {{
            add(new MediaType("text", "plain", StandardCharsets.UTF_8));
        }};
        stringConvert.setSupportedMediaTypes(stringMediaTypes);
        return stringConvert;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(localDateTimeConverter());
        registry.addConverter(localDateConverter());
        registry.addConverter(localTimeConverter());
    }

    /**
     * Json序列化和反序列化转换器，用于转换Post请求体中的json以及将我们的对象序列化为返回响应的json
     */
    @Bean
    public ObjectMapper customDateObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        //LocalDateTime系列序列化和反序列化模块，继承自jsr310，这里修改了日期格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT_yyyy_MM_dd)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT_HH_mm_ss)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT_yyyy_MM_dd)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT_HH_mm_ss)));

        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }


    /**
     * LocalDate转换器，用于转换RequestParam和PathVariable参数
     */
    private Converter<String, LocalDate> localDateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                if (!ObjectUtils.isEmpty(source)) {
                    return LocalDate.parse(source, DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT_yyyy_MM_dd));
                }
                return null;

            }
        };
    }

    /**
     * LocalDateTime转换器，用于转换RequestParam和PathVariable参数
     */
    private Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                if (!ObjectUtils.isEmpty(source)) {
                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss));
                }
                return null;
            }
        };
    }

    /**
     * LocalTime转换器，用于转换RequestParam和PathVariable参数
     */
    private Converter<String, LocalTime> localTimeConverter() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                if (!ObjectUtils.isEmpty(source)) {
                    return LocalTime.parse(source, DateTimeFormatter.ofPattern(DateTimeUtil.DATE_FORMAT_HH_mm_ss));
                }
                return null;
            }
        };
    }

}