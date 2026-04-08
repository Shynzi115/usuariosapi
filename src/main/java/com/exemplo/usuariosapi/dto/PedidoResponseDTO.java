package com.exemplo.usuariosapi.dto;

import com.exemplo.usuariosapi.enums.StatusPedido;

public class PedidoResponseDTO {
    private Long id;
    private StatusPedido status;
    private UsuarioResumoDTO usuario;

    public PedidoResponseDTO(Long id, StatusPedido status, UsuarioResumoDTO usuario) {
        this.id = id;
        this.status = status;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public UsuarioResumoDTO getUsuario() {
        return usuario;
    }

}
