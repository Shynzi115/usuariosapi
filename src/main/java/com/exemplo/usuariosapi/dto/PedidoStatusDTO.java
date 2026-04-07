package com.exemplo.usuariosapi.dto;

import com.exemplo.usuariosapi.enums.StatusPedido;

public class PedidoStatusDTO {
    private StatusPedido status;

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
