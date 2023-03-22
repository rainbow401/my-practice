package com.rainbow.practice.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: YanZhiHao
 * @Date: 2023/3/22
 * @Description:
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory =new SimpleClientHttpRequestFactory();
        //读取超时5秒,默认无限限制,单位：毫秒
        factory.setReadTimeout(10*1000);
        //连接超时10秒，默认无限制，单位：毫秒
        factory.setConnectTimeout(20*1000);
        return factory;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        return restTemplate;
    }


}
