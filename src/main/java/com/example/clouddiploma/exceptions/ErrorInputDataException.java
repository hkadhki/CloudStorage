package com.example.clouddiploma.exceptions;

public class ErrorInputDataException extends IllegalArgumentException{
    public ErrorInputDataException(String msg){
        super(msg);
    }
}
