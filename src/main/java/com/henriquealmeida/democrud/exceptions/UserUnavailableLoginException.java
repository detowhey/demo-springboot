package com.henriquealmeida.democrud.exceptions;

public class UserUnavailableLoginException extends RuntimeException{

    public UserUnavailableLoginException(String message) {
        super(message);
    }
}
