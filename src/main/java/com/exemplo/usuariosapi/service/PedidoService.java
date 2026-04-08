package com.exemplo.usuariosapi.service;

import com.exemplo.usuariosapi.dto.PedidoCreateDTO;
import com.exemplo.usuariosapi.dto.PedidoResponseDTO;
import com.exemplo.usuariosapi.dto.UsuarioResumoDTO;
import com.exemplo.usuariosapi.enums.StatusPedido;
import com.exemplo.usuariosapi.exception.UsuarioNaoEncontradoException;
import com.exemplo.usuariosapi.model.Pedido;
import com.exemplo.usuariosapi.model.Usuario;
import com.exemplo.usuariosapi.repository.PedidoRepository;
import com.exemplo.usuariosapi.repository.UsuarioRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PedidoResponseDTO toDTO(Pedido pedido){
        UsuarioResumoDTO usuarioDTO = new UsuarioResumoDTO(
                pedido.getUsuario().getId(),
                pedido.getUsuario().getNome());
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getStatus(),
                usuarioDTO
        );
    }

    public Pedido criar (PedidoCreateDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(
                        "Usuario "+ dto.getUsuarioId()+ " não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setDescricao(dto.getDescricao());
        pedido.setUsuario(usuario);

        return pedidoRepository.save(pedido);
    }

    public Page<PedidoResponseDTO> listar(Pageable pageable) {
        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
        return pedidos.map(this::toDTO);
    }

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public Pedido atualizarStatus(Long id, StatusPedido status){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(status);

        return pedidoRepository.save(pedido);

    }
}
