package com.example.clouddiploma.exceptions;

public class BadCredentialsException extends IllegalArgumentException{
    public BadCredentialsException(String msg){
        super(msg);
    }
}
