package com.exemplo.usuariosapi.repository;

import com.exemplo.usuariosapi.model.Pedido;
import com.exemplo.usuariosapi.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    boolean existsById(Long id);


    Page<Pedido> findByUsuarioId(Long usuarioId, Pageable pageable);
}
