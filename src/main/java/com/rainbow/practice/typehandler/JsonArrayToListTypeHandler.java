package com.rainbow.practice.typehandler;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;

public class JsonArrayToListTypeHandler extends JacksonTypeHandler {


    public JsonArrayToListTypeHandler(Class<?> type) {
        super(type);
    }
}
