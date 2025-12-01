package com.example.Mercado.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> illegalArgumentException(IllegalArgumentException erro) {
    return ResponseEntity
    .status(HttpStatus.CONFLICT)
    .body(Map.of(
        "message", erro.getMessage(),
        "sucesses", false
        ));
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,Object>> methodArgumentoNotValidExceotion(MethodArgumentNotValidException erro) {
        return ResponseEntity
        .badRequest()
        .body(Map.of(
            "mensagem", erro.getFieldErrors().get(0).getDefaultMessage(),
            "sucesses", false
        ));
    }
    @ExceptionHandler(RuntimeException.class)

    public ResponseEntity<Map<String,Object>> runtimeException(RuntimeException erro) {
        return ResponseEntity
        .badRequest()
        .body(Map.of(
            "mensagem", erro.getMessage(),
            "sucesses", false
        ));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleALLExceptions(Exception erro)  {
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Map.of("mensagem", "Erro no servidor"));

    }
}
