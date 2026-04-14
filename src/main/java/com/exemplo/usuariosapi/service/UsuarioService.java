package com.exemplo.usuariosapi.service;

import com.exemplo.usuariosapi.dto.UsuarioCreateDTO;
import com.exemplo.usuariosapi.dto.UsuarioResponseDTO;
import com.exemplo.usuariosapi.exception.UsuarioNaoEncontradoException;
import com.exemplo.usuariosapi.model.Usuario;
import com.exemplo.usuariosapi.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {this.usuarioRepository = usuarioRepository;}

    private UsuarioResponseDTO toDTO (Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());}

    //Metodo de listagem de usuarios, para buscar todos que existem
    public Page<UsuarioResponseDTO> listar(String nome, Pageable pageable) {
        Page<Usuario> usuarios;

        if (nome != null && !nome.isBlank()) {
            usuarios = usuarioRepository.findByNomeContaining(nome, pageable);
        }else {
            usuarios = usuarioRepository.findAll(pageable);}
        return usuarios.map(this::toDTO);}

    //Metodo de busca por id
    public UsuarioResponseDTO buscarPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->new UsuarioNaoEncontradoException(id));
        return toDTO(usuario);}

    public UsuarioResponseDTO criarUsuario(UsuarioCreateDTO dto){
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());usuario.setEmail(dto.getEmail());usuario.setSenha(dto.getSenha());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuarioSalvo.getId(),usuarioSalvo.getNome(), usuarioSalvo.getEmail());}

    public void deletar(Long id){

        if (!usuarioRepository.existsById(id)){throw new UsuarioNaoEncontradoException(id);}
        usuarioRepository.deleteById(id);}

    public UsuarioResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioResponseDTO dto) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() ->new UsuarioNaoEncontradoException(id));

        if (dto.getId() != null){usuario.setId(dto.getId());}
        if (dto.getNome() != null){usuario.setNome(dto.getNome());}
        if (dto.getEmail() != null){usuario.setEmail(dto.getEmail());}
        usuario = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());}
}