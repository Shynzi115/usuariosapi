package com.exemplo.usuariosapi.exception;

import com.exemplo.usuariosapi.dto.ErroResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErroResponse>tratarUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex){
        ErroResponse erro = new ErroResponse(
                ex.getMessage(),
                404,
                LocalDateTime.now());
            return ResponseEntity
                    .status(404)
                    .body(erro);

        }
    }

