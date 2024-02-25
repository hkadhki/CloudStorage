package com.example.clouddiploma.controller;

import com.example.clouddiploma.dto.ErrorRequest;
import com.example.clouddiploma.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    private int id = 0;

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorRequest> badCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(new ErrorRequest("Bad credentials", id++), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorDeletFileException.class)
    public ResponseEntity<ErrorRequest> errorDeletFileException(ErrorDeletFileException e) {
        return new ResponseEntity<>(new ErrorRequest("Error delet file", id++),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorGettingFileListException.class)
    public ResponseEntity<ErrorRequest> errorGettingFileListException(ErrorGettingFileListException e) {
        return new ResponseEntity<>(new ErrorRequest("Error getting file list", id++),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorInputDataException.class)
    public ResponseEntity<ErrorRequest> errorInputDataException(ErrorInputDataException e) {
        return new ResponseEntity<>(new ErrorRequest("Error input data", id++),HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorUploadFileException.class)
    public ResponseEntity<ErrorRequest> errorUploadFileException(ErrorUploadFileException e) {
        return new ResponseEntity<>(new ErrorRequest("Error upload file", id++),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedErrorException.class)
    public ResponseEntity<ErrorRequest> unauthorizedErrorException(UnauthorizedErrorException e) {
        return new ResponseEntity<>(new ErrorRequest("Unauthorized error", id++),HttpStatus.UNAUTHORIZED);
    }
}
