package com.exemplo.usuariosapi.exception;

import com.exemplo.usuariosapi.enums.StatusPedido;
import org.springframework.http.HttpStatus;

public class RegraNegocioException extends BaseException {
    public RegraNegocioException(Long id, StatusPedido status) {
        super(
                "CHANGE_UNAVAILABLE",
                "Não é possivel alterar o pedido "+id+" para: "+status,
                HttpStatus.BAD_REQUEST
        );
    }
}
