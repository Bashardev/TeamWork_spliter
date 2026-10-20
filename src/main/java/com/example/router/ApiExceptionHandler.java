package com.example.router;

import java.util.Collections;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<Map<String, String>> badRequest(RuntimeException e) {
        String text = e.getMessage() == null ? "bad request" : e.getMessage();
        return ResponseEntity.badRequest().body(Collections.singletonMap("error", text));
    }
}
