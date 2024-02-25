package com.example.clouddiploma.exceptions;

import java.io.IOException;

public class ErrorGettingFileListException extends IOException {
    public ErrorGettingFileListException(String msg){
        super(msg);
    }
}
