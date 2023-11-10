package com.rainbow.practice.designpatterns.chain;

import java.util.ArrayList;
import java.util.List;

public class HandlerChain {

    private final List<IHandler> handlers = new ArrayList<>();

    public void addHandler(IHandler handler) {
        this.handlers.add(handler);
    }

    public void handle() {
        for (IHandler handler : handlers) {
            boolean handled = handler.handle();
            if (handled) {
                break;
            }
        }
    }
}

