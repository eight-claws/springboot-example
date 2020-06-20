package com.jun.cloud.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger2的配置,只在开发环境下存在
 */
@Configuration
@EnableSwagger2
//@ConditionalOnExpression("${swagger.enable:false}==true")
@Profile("dev")
public class Swagger2Config implements WebMvcConfigurer {

    private static List<ResponseMessage> responseMessageList = new ArrayList<>();
    static {
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());
        responseMessageList.add(new ResponseMessageBuilder().code(403).message("Forbidden").build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("Not Found").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("Server Error").build());
    }

    /**
     * 组件内部接口
     * (groupName不要写中文)
     */
    @Bean
    public Docket webRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("1.restful_web")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jun.cloud"))

                //不匹配以/api/开头的接口路径
                .paths(PathSelectors.regex("^((?!/api/).)*$"))
                .build()
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList);
    }

    /**
     * 组件api接口
     */
    @Bean
    public Docket openRestApi() {
        //统一在请求头中增加token
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token").description("令牌")
                .modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("2.restful_api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jun.cloud.api"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                ;
    }

    //构建api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("项目API文档")
                //版本号
                .version("1.0")
                //描述
                .description("API接口文档，供前后端沟通使用")
                //创建人
                .contact(new Contact("余生君", "https://blog.csdn.net/java_collect", ""))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");

    }


    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    //@Bean
    //public Docket createRestApi() {
    //    return new Docket(DocumentationType.SWAGGER_2)
    //            .apiInfo(apiInfo())
    //            .select()
    //            //为当前包路径
    //            .apis(RequestHandlerSelectors.basePackage("com.jun.cloud"))
    //            .paths(PathSelectors.any())
    //            .build();
    //}
}

