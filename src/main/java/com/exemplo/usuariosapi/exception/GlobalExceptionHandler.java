package com.exemplo.usuariosapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;

import com.exemplo.usuariosapi.dto.ErroResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex, HttpServletRequest request) {

        ErroResponse erro = new ErroResponse(
                "USER_NOT_FOUND",
                ex.getMessage(),
                404,
                LocalDateTime.now(),
                request.getRequestURI(),
                List.of() // ou null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<ErroResponse> tratarEmailDuplicado(EmailDuplicadoException ex, HttpServletRequest request){
        ErroResponse erro = new ErroResponse(
                "EMAIL_DUPLICADO",
                ex.getMessage(),
                400,
                LocalDateTime.now(),
                request.getRequestURI(),
                List.of()//sem lista de erros
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarValidacao(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ErroResponse erro = new ErroResponse(
                "VALIDATION_ERROR",
                "Erro de validação",
                400,
                LocalDateTime.now(),
                request.getRequestURI(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}

