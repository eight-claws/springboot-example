package com.jun.sail.appstart.myimport;

import com.jun.sail.appstart.myimport.config.EnableCustomConfig;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SpringUtilImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // importingClassMetadata指 使用@Import时 @Import注解所在类的元数据
        String enableCustomConfig = EnableCustomConfig.class.getName();
        if (importingClassMetadata.hasAnnotation(enableCustomConfig)) {
            Map<String, Object> attrs = importingClassMetadata.getAnnotationAttributes(enableCustomConfig);
            if (Objects.nonNull(attrs)) {
                String registerUtil = Optional.ofNullable(attrs.get("flag")).map(Object::toString).orElse("false");
                // 如果为true，就去加载SpringUtil对象进容器
                if (Boolean.parseBoolean(registerUtil)) {
                    return new String[]{SpringUtil.class.getName()};
                }
            }
        }
        return new String[]{};
    }
}