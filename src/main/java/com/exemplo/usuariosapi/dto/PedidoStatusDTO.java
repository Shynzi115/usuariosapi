package com.exemplo.usuariosapi.dto;

import com.exemplo.usuariosapi.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;

public class PedidoStatusDTO {

    @NotNull(message = "O status é obrigatório")
    private StatusPedido status;
    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
