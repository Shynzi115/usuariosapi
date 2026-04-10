package com.exemplo.usuariosapi.enums;

public enum StatusPedido {
    PENDENTE,
    CRIADO,
    PAGO,
    ENVIADO,
    CANCELADO;

    public boolean podeAlterarPara(StatusPedido novoStatus) {
        return switch (this) {
            case PENDENTE -> novoStatus == CRIADO || novoStatus == CANCELADO;
            case CRIADO -> novoStatus == PAGO || novoStatus == CANCELADO;
            case PAGO -> novoStatus == ENVIADO;
            case ENVIADO, CANCELADO -> false;
        };
    }
}