package com.exemplo.usuariosapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import com.exemplo.usuariosapi.dto.ErroResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErroResponse criarErro(
            String code,
            String message,
            HttpStatus status,
            String path,
            List<String>errors
    ){return new ErroResponse(
            code,
            message,
            status.value(),
            LocalDateTime.now(),
            path,
            errors);}
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErroResponse> tratarBaseException(BaseException ex, HttpServletRequest request){
        ErroResponse erro = criarErro(
                ex.getCode(),
                ex.getMessage(),
                ex.getStatus(),
                request.getRequestURI(),
                Collections.emptyList()
        ); return ResponseEntity
                .status(ex.getStatus())
                .body(erro);}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarValidacao(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ErroResponse erro = criarErro(
                "VALIDATION_ERROR",
                "Erro de validação",
                HttpStatus.BAD_REQUEST,
                request.getRequestURI(),
                errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> tratarErroGenerico(Exception ex, HttpServletRequest request){
        ErroResponse erro = criarErro(
                "INTERNAL_ERROR",
                "Erro interno inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR,
                request.getRequestURI(),
                Collections.emptyList());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);}

}

