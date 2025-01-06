package com.userentity.GlobalExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userentity.exceptions.ResourceNotFoundExceptions;

@RestControllerAdvice
public class ExceptionsHandler {

  @ExceptionHandler(ResourceNotFoundExceptions.class)
  public ResponseEntity<Map<String, Object>> resourceNotFundResponseEntity(ResourceNotFoundExceptions e) {

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", e.getMessage());
    map.put("success", "false");

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
  }
}
