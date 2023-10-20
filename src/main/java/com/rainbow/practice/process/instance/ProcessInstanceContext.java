package com.rainbow.practice.process.instance;

import java.util.HashMap;

/**
 * @Author: yzh
 * @Date: 2023/10/20
 * @Description:
 */
public class ProcessInstanceContext {

    private final HashMap<String, Object> variables;

    public ProcessInstanceContext(HashMap<String, Object> variables) {
        this.variables = variables;
    }

    public ProcessInstanceContext() {
        variables = new HashMap<>();
    }

    public void addVariable(String key, Object variable) {
        variables.put(key, variable);
    }

    public void removeVariable(String key, Object variable) {
        variables.remove(key, variable);
    }

    public Object getVariable(String key) {
        return variables.get(key);
    }
}
