package com.exemplo.usuariosapi.exception;

import org.springframework.http.HttpStatus;

public class UsuarioNaoEncontradoException extends BaseException {

    public UsuarioNaoEncontradoException(Long id) {
        super (
                "USER_NOT_FOUND",
                "Usuario com ID "+ id+ "não encontrado",
                HttpStatus.NOT_FOUND
        );

    }
}
