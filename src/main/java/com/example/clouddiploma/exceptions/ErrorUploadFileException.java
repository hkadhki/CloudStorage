package com.example.clouddiploma.exceptions;

import java.io.IOException;

public class ErrorUploadFileException extends IOException {
    public ErrorUploadFileException(String msg){
        super(msg);
    }
}
