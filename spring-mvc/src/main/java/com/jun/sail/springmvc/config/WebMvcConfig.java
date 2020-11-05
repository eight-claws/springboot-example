package com.jun.sail.springmvc.config;

import com.jun.sail.springmvc.web.LocalDateTimeConverter;
import com.jun.sail.springmvc.web.LocalDateTimeFormatter;
import com.jun.sail.springmvc.web.enums.CodedEnumConverterFactory;
import org.springframework.context.annotation.Configuration;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * WebMvc配置：
 * 1.消息转换器的配置，提供Jackson的支持
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 无论枚举还是LocalDateTime，Converter都是只负责@PathVariable和@RequestParam中的参数
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 处理枚举的Converter。@RequestBody是@JsonValue生效的
        registry.addConverterFactory(new CodedEnumConverterFactory());

        // 处理时间的Converter，这里负责的是@PathVariable和@RequestParam的时间参数转化。@RequestBody里的时间参数反序列化spring已经提供，不过格式是2011-12-03T10:15:30
        // registry.addConverter(LocalDateTimeConverter.localDateTimeConverter());
        // registry.addConverter(LocalDateTimeConverter.localDateConverter());
        // registry.addConverter(LocalDateTimeConverter.localTimeConverter());

        registry.addFormatter(LocalDateTimeFormatter.localDateFormatter());
        registry.addFormatter(LocalDateTimeFormatter.localTimeFormatter());
        registry.addFormatter(LocalDateTimeFormatter.localDateTimeFormatter());
    }


}