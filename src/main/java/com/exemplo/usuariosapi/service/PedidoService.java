package com.exemplo.usuariosapi.service;

import com.exemplo.usuariosapi.dto.PedidoCreateDTO;
import com.exemplo.usuariosapi.dto.PedidoResponseDTO;
import com.exemplo.usuariosapi.dto.UsuarioResumoDTO;
import com.exemplo.usuariosapi.enums.StatusPedido;
import com.exemplo.usuariosapi.exception.PedidoNaoEncontradoException;
import com.exemplo.usuariosapi.exception.RegraNegocioException;
import com.exemplo.usuariosapi.exception.UsuarioNaoEncontradoException;
import com.exemplo.usuariosapi.model.Pedido;
import com.exemplo.usuariosapi.model.Usuario;
import com.exemplo.usuariosapi.repository.PedidoRepository;
import com.exemplo.usuariosapi.repository.UsuarioRepository;
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

    public PedidoResponseDTO criar(PedidoCreateDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(
                        "Usuario "+ dto.getUsuarioId()+ " não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setDescricao(dto.getDescricao());
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.PENDENTE);

        return toDTO(pedidoRepository.save(pedido));
    }

    public Page<PedidoResponseDTO> listar(Pageable pageable) {
        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
        return pedidos.map(this::toDTO);
    }

    public PedidoResponseDTO atualizarStatus(Long id, StatusPedido status){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(()-> new PedidoNaoEncontradoException("Pedido não encontrado"));

        if (!pedido.getStatus().podeAlterarPara(status)){
            throw new RegraNegocioException(
                    "Não é possivel alterar de "+ pedido.getStatus()+" para: "+status
            );
        }
        pedido.setStatus(status);
        return toDTO(pedidoRepository.save(pedido));
    }
}
