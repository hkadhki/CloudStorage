package com.example.clouddiploma.exceptions;

import java.io.IOException;

public class ErrorDeletFileException extends IOException {
    public ErrorDeletFileException(String msg){
        super(msg);
    }
}
