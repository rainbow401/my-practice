package com.rainbow.practice.process.instance;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author: yzh
 * @Date: 2023/9/20
 * @Description:
 */
@Data
public class ProcessInstance {

    private String id;

    private String defineId;

    private final ProcessInstanceContext context;

    public ProcessInstance() {
        context = new ProcessInstanceContext();
    }

    public ProcessInstance(HashMap<String, Object> variables) {
        context = new ProcessInstanceContext(variables);
    }
}
