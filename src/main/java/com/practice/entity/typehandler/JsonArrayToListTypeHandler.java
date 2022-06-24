package com.practice.entity.typehandler;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;

public class JsonArrayToListTypeHandler extends JacksonTypeHandler {


    public JsonArrayToListTypeHandler(Class<?> type) {
        super(type);
    }
}
