package com.exemplo.usuariosapi.exception;

import org.springframework.http.HttpStatus;

public class EmailDuplicadoException extends BaseException {
    public EmailDuplicadoException(String email) {
        super(
                "EMAIL_ALREADY_EXISTS",
                "E-mail já cadastrado",
                HttpStatus.BAD_REQUEST
        );
    }
}
