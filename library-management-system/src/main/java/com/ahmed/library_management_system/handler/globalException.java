package com.ahmed.library_management_system.handler;

import com.ahmed.library_management_system.exception.BOOKEXISTSEXCPTION;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class globalException {

    @ExceptionHandler(BOOKEXISTSEXCPTION.class)
    public ResponseEntity<String> handleBookExist(BOOKEXISTSEXCPTION bx){
        return ResponseEntity.status(400).body(bx.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,List<String >>> handleValidationError(
            MethodArgumentNotValidException ex
    ){
        Map<String, List<String>> errors = new HashMap<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()){

            String fieldName = error.getField();
            String message = error.getDefaultMessage();

            errors.computeIfAbsent(fieldName, k->new ArrayList<>()).add(message);
        }

        return ResponseEntity.status(400).body(errors);
    }
}
