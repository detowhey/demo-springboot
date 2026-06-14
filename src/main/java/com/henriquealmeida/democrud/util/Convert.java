package com.henriquealmeida.democrud.util;

import org.modelmapper.ModelMapper;

public class Convert {

    private final ModelMapper modelMapper = new ModelMapper();
    private static Convert instance;

    private Convert() {
    }

    public static Convert getInstance() {
        if (instance == null)
            instance = new Convert();
        return instance;
    }

    public <T> T convertToType(Object source, Class<T> resultClass) {
        return modelMapper.map(source, resultClass);
    }
}
