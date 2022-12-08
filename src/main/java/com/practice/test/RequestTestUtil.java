package com.practice.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

/**
 * 单元测试工具类，可以模拟带token的请求
 * @author yanzhihao
 */
@Slf4j
@Component
public class RequestTestUtil {

    private MockMvc mockMvc;

    private final WebApplicationContext wac;

    public RequestTestUtil(WebApplicationContext webApplicationContext) {
        this.wac = webApplicationContext;
    }

    private String TOKEN = "";

    private final String TOKEN_FILE_PATH = "*/token.json";


    @PostConstruct
    public void initToken() throws Exception {
        try {
            Resource resource = new PathResource(TOKEN_FILE_PATH);
            this.TOKEN = JsonUtil.jsonRead(resource.getFile());
            if (StringUtils.isBlank(this.TOKEN)) {
                throw new Exception("token为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化{}失败", RequestTestUtil.class);
        }

    }

    public String post(String url, String body, MultiValueMap<String, String> queryParams, MultiValueMap<String, String> params) throws Exception {
        RequestBuilder request = generatorRequest(HttpMethod.POST, url, body, queryParams, params);
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        checkHttpStatus(response);
        return formatJson(response.getContentAsString(StandardCharsets.UTF_8));
    }

    public String post(String url, String body) throws Exception {
        return post(url, body, new LinkedMultiValueMap<>(), new LinkedMultiValueMap<>());
    }

    public Object get(String url, String body, MultiValueMap<String, String> queryParams, MultiValueMap<String, String> params) throws Exception {
        RequestBuilder request = generatorRequest(HttpMethod.GET, url, body, queryParams, params);
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        checkHttpStatus(response);
        return formatJson(response.getContentAsString(StandardCharsets.UTF_8));
    }

    public String put(String url, String body, MultiValueMap<String, String> queryParams, MultiValueMap<String, String> params) throws Exception {
        RequestBuilder request = generatorRequest(HttpMethod.PUT, url, body, queryParams, params);
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        checkHttpStatus(response);
        return formatJson(response.getContentAsString(StandardCharsets.UTF_8));
    }

    public String put(String url, String body) throws Exception {
        return put(url, body, new LinkedMultiValueMap<>(), new LinkedMultiValueMap<>());
    }

    public String delete(String url, String body, MultiValueMap<String, String> queryParams, MultiValueMap<String, String> params) throws Exception {
        RequestBuilder request = generatorRequest(HttpMethod.DELETE, url, body, queryParams, params);
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        checkHttpStatus(response);
        return formatJson(response.getContentAsString(StandardCharsets.UTF_8));
    }

    public RequestBuilder generatorRequest(HttpMethod method, String url, String body,
                                           MultiValueMap<String, String> queryParams,
                                           MultiValueMap<String, String> params) {

        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        MockHttpServletRequestBuilder request = null;
        if (HttpMethod.GET.equals(method)) {
            request = MockMvcRequestBuilders.get(url);
        } else if (HttpMethod.POST.equals(method)) {
            request = MockMvcRequestBuilders.post(url);
        } else if (HttpMethod.PUT.equals(method)) {
            request = MockMvcRequestBuilders.put(url);
        } else if (HttpMethod.DELETE.equals(method)) {
            request = MockMvcRequestBuilders.delete(url);
        } else {
            request = MockMvcRequestBuilders.post(url);
        }

        if (StringUtils.isNotBlank(body)) {
            request.content(body);
        }

        request.queryParams(queryParams);
        request.params(params);

        return request;
    }

    private static void checkHttpStatus(MockHttpServletResponse response) throws Exception {
        int status = response.getStatus();
        if (status != HttpStatus.OK.value()) {
            throw new Exception("请求失败");
        }
    }

    public static String formatJson(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        return JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
    }
}
