package com.rainbow.practice.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YanZhiHao
 * @Date: 2023/3/22
 * @Description:
 */
@Slf4j
@Component
public class RestTemplateClient {

    private final RestTemplate restTemplate;

    public RestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private <T, R> T post(String url, @Nullable MultiValueMap<String, String> params, @Nullable R body, HashMap<String, String> headers, Class<T> clazz) throws Exception {
        log.info("发送请求 url: {}, params: {}, body: {}, headers: {}", url, params, body, headers);

        HttpHeaders header = new HttpHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            header.add(key, value);
        }

        // 构造body和header
        HttpEntity<R> entity = new HttpEntity<>(body, header);

        // 构造params参数
        if (!CollectionUtils.isEmpty(params)) {
            UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            url = queryBuilder.toUriString();
        }

        ResponseEntity<T> response = null;

        // 状态码为4xx/5xx时会抛出异常
        try {
            response = restTemplate.postForEntity(url, entity, clazz);
        } catch (Exception e) {
            log.error("请求时失败 message: {}", e.getMessage());
            throw new RuntimeException();
        }

        // 状态码不是200时请求失败 抛出异常
        HttpStatus statusCode = response.getStatusCode();
        if (HttpStatus.OK.equals(statusCode)) {
            log.info("请求成功 responseBody: {}", response.getBody());
            return response.getBody();
        } else {
            log.error("请求失败 httpCode: {}", statusCode.value());
            throw new Exception();
        }
    }

    private <T, R> T get(String url, @Nullable MultiValueMap<String, String> params, @Nullable R body, HashMap<String, String> headers, Class<T> clazz) throws Exception {
        log.info("发送请求 url: {}, params: {}, body: {}, headers: {}", url, params, body, headers);

        HttpHeaders header = new HttpHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            header.add(key, value);
        }

        // 构造body和header
        HttpEntity<R> entity = new HttpEntity<>(body, header);

        // 构造params参数
        if (!CollectionUtils.isEmpty(params)) {
            UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            url = queryBuilder.toUriString();
        }

        ResponseEntity<T> response = null;

        // 状态码为4xx/5xx时会抛出异常
        try {
            response = restTemplate.getForEntity(url, clazz);
        } catch (Exception e) {
            log.error("请求时失败 message: {}", e.getMessage());
            throw new RuntimeException();
        }

        // 状态码不是200时请求失败 抛出异常
        HttpStatus statusCode = response.getStatusCode();
        if (HttpStatus.OK.equals(statusCode)) {
            log.info("请求成功 responseBody: {}", response.getBody());
            return response.getBody();
        } else {
            log.error("请求失败 httpCode: {}", statusCode.value());
            throw new Exception();
        }
    }

    public <T, R> T postWithToken(String url, @Nullable MultiValueMap<String, String> params, @Nullable R body, Class<T> clazz) throws Exception {
        HashMap<String, String> headers = new HashMap<>(1);
        headers.put("Content-Type", "application/json");
        return post(url, params, body, headers, clazz);
    }

    public <T, R> T postXmlWithToken(String url, @Nullable MultiValueMap<String, String> params, @Nullable R body, Class<T> clazz) throws Exception {
        HashMap<String, String> headers = new HashMap<>(2);
        headers.put("Content-Type", "application/xml");
        return post(url, params, body, headers, clazz);
    }

    public <T, R> T getWithToken(String url, @Nullable MultiValueMap<String, String> params, Class<T> clazz) throws Exception {
        HashMap<String, String> headers = new HashMap<>(1);
        return post(url, params, null, headers, clazz);
    }

    public <T, R> T putWithToken(String url, @Nullable MultiValueMap<String, String> params, @Nullable R body, Class<T> clazz) throws Exception {
        HashMap<String, String> headers = new HashMap<>(1);
        return post(url, params, body, headers, clazz);
    }

    public <T, R> T deleteWithToken(String url, @Nullable MultiValueMap<String, String> params, @Nullable R body, Class<T> clazz) throws Exception {
        HashMap<String, String> headers = new HashMap<>(1);
        return post(url, params, body, headers, clazz);
    }
}
