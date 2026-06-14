package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.util.Convert;

public abstract class BaseController {

    private final Convert convert = Convert.getInstance();

    public <T> T convertToType(Object source, Class<T> resultClass) {
        return convert.convertToType(source, resultClass);
    }
}
