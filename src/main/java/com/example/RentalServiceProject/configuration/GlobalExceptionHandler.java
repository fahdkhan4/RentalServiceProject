package com.example.RentalServiceProject.configuration;

import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.sun.javafx.iio.ImageStorageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValid(MethodArgumentNotValidException error){
            return  new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(ContentNotFoundException error){
        return new ResponseEntity<>(error.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageStorageException.class)
    public ResponseEntity<String> imageStorageException(ImageStorageException error){
        return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> fileNotFoundException(FileNotFoundException fileNotFoundException){
        return new ResponseEntity<>(fileNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }




}
