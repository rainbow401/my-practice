package com.rainbow.practice.typehandler;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

public class BelongCompanyTypeHandler extends JacksonTypeHandler {
    public BelongCompanyTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    protected Object parse(String json) {
        try {
            return getObjectMapper().readValue(json, new TypeReference<List<BelongCompanyVO>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}