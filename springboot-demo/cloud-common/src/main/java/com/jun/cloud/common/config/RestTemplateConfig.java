package com.jun.cloud.common.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@ConditionalOnClass(value = {RestTemplate.class, HttpClient.class})
public class RestTemplateConfig {

  @Value("${remote.maxTotalConnect:0}")
  private int maxTotalConnect; //连接池的最大连接数默认为0

  @Value("${remote.maxConnectPerRoute:200}")
  private int maxConnectPerRoute; //单个主机的最大连接数

  @Value("${remote.connectTimeout:2000}")
  private int connectTimeout; //连接超时默认2s

  @Value("${remote.readTimeout:30000}")
  private int readTimeout; //读取超时默认30s

  /**
   * 初始化RestTemplate
   * @return RestTemplate
   */
  @Bean
  @ConditionalOnMissingBean(RestTemplate.class)
  public RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplate(this.createFactory());

    //重新设置StringHttpMessageConverter字符集为UTF-8，解决中文乱码问题
    List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
    HttpMessageConverter<?> converterTarget = null;
    for (HttpMessageConverter<?> item : converterList) {
      if (StringHttpMessageConverter.class == item.getClass()) {
        converterTarget = item;
        break;
      }
    }
    if (null != converterTarget) {
      converterList.remove(converterTarget);
    }
    converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

    //加入FastJson转换器
    //converterList.add(new FastJsonHttpMessageConverter4());
    return restTemplate;
  }

  //创建HTTP客户端工厂
  private ClientHttpRequestFactory createFactory() {
    if (this.maxTotalConnect <= 0) {
      SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
      factory.setConnectTimeout(this.connectTimeout);
      factory.setReadTimeout(this.readTimeout);
      return factory;
    }
    HttpClient httpClient = HttpClientBuilder.create()
            .setMaxConnTotal(this.maxTotalConnect)
            .setMaxConnPerRoute(this.maxConnectPerRoute).build();
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    factory.setConnectTimeout(this.connectTimeout);
    factory.setReadTimeout(this.readTimeout);
    return factory;
  }


  /**
   *  spring的json转换器默认使用的是Jackson，json字符串和对应的Entity如果有字段对不上就会报错，这个有点不符合国情，
   *  而FastJson则不会报错，所以很多时候都会用FastJSON替换默认的Jackson。
   */
  //@Bean
  //public HttpMessageConverters fastJsonConverter() {
  //  FastJsonConfig fastJsonConfig = new FastJsonConfig();
  //  //自定义格式化输出
  //  fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
  //          SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero);
  //
  //  FastJsonHttpMessageConverter4 fastjson = new FastJsonHttpMessageConverter4();
  //  fastjson.setFastJsonConfig(fastJsonConfig);
  //  return new HttpMessageConverters(fastjson);
  //}

}