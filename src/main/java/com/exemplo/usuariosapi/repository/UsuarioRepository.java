package com.exemplo.usuariosapi.repository;

import com.exemplo.usuariosapi.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
}
