package com.example.clouddiploma.exceptions;

public class UnauthorizedErrorException extends IllegalArgumentException{
    public UnauthorizedErrorException(String msg){
        super(msg);
    }
}
