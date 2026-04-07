package com.exemplo.usuariosapi.service;
import com.exemplo.usuariosapi.dto.UsuarioCreateDTO;
import com.exemplo.usuariosapi.dto.UsuarioDTO;
import com.exemplo.usuariosapi.dto.UsuarioUpdateDTO;
import com.exemplo.usuariosapi.exception.UsuarioNaoEncontradoException;
import com.exemplo.usuariosapi.model.Usuario;
import com.exemplo.usuariosapi.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {this.usuarioRepository = usuarioRepository;}

    //Metodo de listagem de usuarios, para buscar todos que existem
    public Page<Usuario> listar(String nome, Pageable pageable) {

        if (nome != null && !nome.isBlank()) {
            return usuarioRepository.findByNomeContaining(nome, pageable);
        }
        return usuarioRepository.findAll(pageable);}

    //Metodo de busca por id
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() ->new UsuarioNaoEncontradoException("Usuario "+id+" não encontrado"));}

    public UsuarioDTO criarUsuario(UsuarioCreateDTO dto){
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());usuario.setEmail(dto.getEmail());usuario.setSenha(dto.getSenha());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuarioSalvo.getId(),usuarioSalvo.getNome(), usuarioSalvo.getEmail());}

    public void deletar(Long id){

        if (!usuarioRepository.existsById(id)){throw new RuntimeException("Usuario não encontrado.");}
        usuarioRepository.deleteById(id);}

    public UsuarioDTO atualizar(Long id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() ->new RuntimeException("Usuario não encontrado."));

        if (dto.getNome() != null){usuario.setNome(dto.getNome());}
        if (dto.getEmail() != null){usuario.setEmail(dto.getEmail());}
        if (dto.getSenha() != null){usuario.setSenha(dto.getSenha());}
        usuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSenha());}
}