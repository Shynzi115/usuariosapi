package com.exemplo.usuariosapi.exception;

import org.springframework.http.HttpStatus;

public class PedidoNaoEncontradoException extends BaseException {

    public PedidoNaoEncontradoException(Long id) {
        super(
                "ORDER_NOT_FOUND",
                "Pedido com ID" +id+ " não encontrado",
                HttpStatus.BAD_REQUEST
        );
    }
}
