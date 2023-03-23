package com.rainbow.practice.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    private <T, R> T post(String url, @Nullable R body, HashMap<String, String> headers, Class<T> clazz, MultiValueMap<String, String> params) throws Exception {
        url = generateParams(url, params);
        printParamLog(url, body, headers, params);

        // 构造body和header
        HttpHeaders header = generateHeader(headers);
        HttpEntity<R> entity = new HttpEntity<>(body, header);

        ResponseEntity<T> response = null;

        // 状态码为4xx/5xx时会抛出异常
        try {
            response = restTemplate.postForEntity(url, entity, clazz);
        } catch (Exception e) {
            log.error("请求时失败 message: {}", e.getMessage());
            throw new RuntimeException("请求失败");
        }

        // 状态码不是200时请求失败 抛出异常
        HttpStatus statusCode = response.getStatusCode();
        if (HttpStatus.OK.equals(statusCode)) {
            log.info("请求成功 responseBody: {}", response.getBody());
            return response.getBody();
        } else {
            log.error("请求失败 httpCode: {}", statusCode.value());
            throw new Exception("请求失败");
        }
    }

    private <T> T get(String url, HashMap<String, String> headers, Class<T> clazz, MultiValueMap<String, String> params) throws Exception {
        url = generateParams(url, params);
        printParamLog(url, null, headers, params);

        // 构造body和header
        HttpHeaders header = generateHeader(headers);
        HttpEntity<Object> entity = new HttpEntity<>(null, header);

        ResponseEntity<T> response = null;


        // 状态码为4xx/5xx时会抛出异常
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求时失败 message: {}", e.getMessage());
            throw new RuntimeException("请求失败");
        }

        // 状态码不是200时请求失败 抛出异常
        HttpStatus statusCode = response.getStatusCode();
        if (HttpStatus.OK.equals(statusCode)) {
            log.info("请求成功 responseBody: {}", response.getBody());
            return response.getBody();
        } else {
            log.error("请求失败 httpCode: {}", statusCode.value());
            throw new Exception("请求失败");
        }
    }

    private <R> void put(String url, @Nullable R body, HashMap<String, String> headers, MultiValueMap<String, String> params) {
        url = generateParams(url, params);
        printParamLog(url, body, headers, params);

        // 构造body和header
        HttpHeaders header = generateHeader(headers);
        HttpEntity<R> entity = new HttpEntity<>(body, header);

        // 状态码为4xx/5xx时会抛出异常
        try {
            restTemplate.put(url, entity);
        } catch (Exception e) {
            log.error("请求时失败 message: {}", e.getMessage());
            throw new RuntimeException("请求失败");
        }
    }

    private <R> void delete(String url, @Nullable R body, HashMap<String, String> headers, MultiValueMap<String, String> params) {
        url = generateParams(url, params);
        printParamLog(url, body, headers, params);

        // 构造body和header
        HttpHeaders header = generateHeader(headers);
        HttpEntity<R> entity = new HttpEntity<>(body, header);

        // 状态码为4xx/5xx时会抛出异常
        try {
            restTemplate.delete(url, entity, params);
        } catch (Exception e) {
            log.error("请求时失败 message: {}", e.getMessage());
            throw new RuntimeException("请求失败");
        }
    }

    private static <R> void printParamLog(String url, R body, HashMap<String, String> headers, Map<String, ?> params) {
        log.info("发送请求 url: {}, params: {}, body: {}, headers: {}", url, params, body, headers);
    }

    private HttpHeaders generateHeader(HashMap<String, String> headers) {
        HttpHeaders header = new HttpHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            header.add(key, value);
        }
        return header;
    }

    private String generateParams(String url, MultiValueMap<String, String> params) {

        // 构造params参数
        if (!CollectionUtils.isEmpty(params)) {
            UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            url = queryBuilder.toUriString();
        }

        return url;
    }

    public <T, R> T postWithToken(String url, @Nullable R body, Class<T> clazz, MultiValueMap<String, String> params) throws Exception {
        HashMap<String, String> headers = new HashMap<>(1);
        headers.put("Content-Type", "application/json");
        return post(url, body, headers, clazz, params);
    }

    public <T, R> T postWithToken(String url, Class<T> clazz) throws Exception {
        return postWithToken(url, null, clazz, null);
    }

    public <T, R> T postWithToken(String url, @Nullable R body,Class<T> clazz) throws Exception {
        return postWithToken(url, body, clazz, null);
    }

    public <T, R> T postWithToken(String url, Class<T> clazz, MultiValueMap<String, String> params) throws Exception {
        return postWithToken(url, null, clazz, params);
    }

    public <T, R> T postXmlWithToken(String url, @Nullable R body, Class<T> clazz, MultiValueMap<String, String> params) throws Exception {
        HashMap<String, String> headers = new HashMap<>(2);
        headers.put("Content-Type", "application/xml");
        return post(url, body, headers, clazz, params);
    }

    public <T> T getWithToken(String url, Class<T> clazz, MultiValueMap<String, String> params) throws Exception {
        HashMap<String, String> headers = new HashMap<>(1);
        headers.put("Content-Type", "text/html;charset=utf-8");
        return get(url, headers, clazz, params);
    }

    public <T> T getWithToken(String url, Class<T> clazz) throws Exception {
        return getWithToken(url, clazz, null);
    }

    public <R> void putWithToken(String url, @Nullable R body, MultiValueMap<String, String> params) {
        HashMap<String, String> headers = new HashMap<>(1);
        headers.put("Content-Type", "application/json");
        put(url, body, headers, params);
    }

    public void putWithToken(String url) {
        putWithToken(url, null, null);
    }

    public <R> void putWithToken(String url, @Nullable R body) {
        putWithToken(url, body, null);
    }

    public void putWithToken(String url, MultiValueMap<String, String> params) {
        putWithToken(url, null, params);
    }

    public <R> void deleteWithToken(String url, @Nullable R body, MultiValueMap<String, String> params) {
        HashMap<String, String> headers = new HashMap<>(1);
        headers.put("Content-Type", "application/json");
        delete(url, body, headers, params);
    }

    public void deleteWithToken(String url, MultiValueMap<String, String> params) {
        deleteWithToken(url, null, params);
    }
}
