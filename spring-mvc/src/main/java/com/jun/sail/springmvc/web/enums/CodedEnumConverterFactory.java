package com.jun.sail.springmvc.web.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 参考自https://blog.csdn.net/alinyua/article/details/86383254
 * <p>
 * 对于PathVariable，RequestParam，spring默认的枚举处理器是StringToEnumConverterFactory，是按name()来匹配的
 * 对于@RequestBody ，spring默认的先按字面量反序列化，然后如果是int，再按枚举的ordinal反序列化，否则抛异常
 *
 * @Author wangjun
 * @Date 2020/11/4
 **/
@SuppressWarnings({"unchecked", "rawtypes"})
public class CodedEnumConverterFactory implements ConverterFactory<String, CodeEnum> {

    /**
     * 根据目标类型获取相应的转换器
     *
     * @param targetType 目标类型
     * @param <T>        CodedEnum的实现类
     */
    @Override
    public <T extends CodeEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StrToEnumConverter(targetType);
    }

    /**
     * 将str对应的字符串转换为目标类型的转换器
     *
     * @param <T> 目标类型（CodedEnum的实现类）
     */
    private final class StrToEnumConverter<T extends CodeEnum> implements Converter<String, T> {
        private Class<T> enumType;

        private StrToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            for (T e : enumType.getEnumConstants()) {
                // 先根据getCode的返回值去匹配
                if (e.matchCode().equals(source)) {
                    return e;
                }
            }
            // T cast = enumType.cast(CodeEnum.class);
            // if (cast.matchEnumName()) {
            //     for (T e : enumType.getEnumConstants()) {
            //         // 如果上面匹配不到，根据枚举字面量去匹配。或者像下面直接抛出异常也可以
            //         if (((Enum) e).name().equals(source)) {
            //             return e;
            //         }
            //     }
            // }

            // 这里抛出异常后会调用spring默认的转换方案，即按字面量去转换
            throw new IllegalArgumentException();
        }
    }

}
