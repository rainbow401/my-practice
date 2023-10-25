package com.rainbow.practice.designpatterns.strategy;

import java.util.HashMap;

public class StrategyFactory {

    private static final Map<String, Strategy> strategies = new HashMap<>();

    static {
        strategies.put("A", new ConcreteStrategyA());
        strategies.put("B", new ConcreteStrategyB());
    }

    public static Strategy getStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("they should not be empty");
        }

        return strategies.get(type);
    }
}
