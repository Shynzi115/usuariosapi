package com.exemplo.usuariosapi.service;

import com.exemplo.usuariosapi.UsuarioDTO.UsuarioDTO;
import com.exemplo.usuariosapi.model.Usuario;
import com.exemplo.usuariosapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario){return usuarioRepository.save(usuario);}

    public List<UsuarioDTO> listar(){

        //Pega os usuarios do banco
        List<Usuario> usuarios  = usuarioRepository.findAll();

        // Cria a lista de DTOs
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        // Converte cada Usuario em UsuarioDTO
        for (Usuario u : usuarios){
            UsuarioDTO dto = new UsuarioDTO(u.getId(), u.getNome(), u.getEmail());
            usuariosDTO.add(dto);
        }
        // Retorna lista de DTOs
        return usuariosDTO;
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public void deletar(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());

            return usuarioRepository.save(usuario);
        }

        return null;
    }
}
