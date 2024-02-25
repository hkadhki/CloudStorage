package com.example.clouddiploma.dto;

public class ErrorRequest {
    String msg;
    int id;

    public ErrorRequest(String msg, int id){
        this.msg = msg;
        this.id = id;
    }
}
