package com.hotel.hotel.globalExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hotel.hotel.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException e) {

    Map<String, Object> map = new HashMap<String, Object>();

    map.put("Message", e.getMessage());
    map.put("STATUS_CODE", HttpStatus.NOT_FOUND);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);

  }
}
