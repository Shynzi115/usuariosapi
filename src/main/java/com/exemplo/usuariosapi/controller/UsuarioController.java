package com.exemplo.usuariosapi.controller;
import com.exemplo.usuariosapi.dto.UsuarioCreateDTO;
import com.exemplo.usuariosapi.dto.UsuarioDTO;
import com.exemplo.usuariosapi.dto.UsuarioUpdateDTO;
import com.exemplo.usuariosapi.model.Usuario;
import com.exemplo.usuariosapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController @RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){this.usuarioService = usuarioService;}
    //Get para listar todos os usuarios do banco, com paginação
    @GetMapping
    public Page<Usuario> listar(@RequestParam(required = false) String nome, Pageable pageable) {
        return usuarioService.listar(nome, pageable);}

    //Busca de usuarios com GET e PathVariable para buscar por id
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);}

    //Post para inserir usuarios
    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioCreateDTO dto){
        UsuarioDTO usuario = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(201).body(usuario);}

    //Deletar usuarios com DELETE
    @DeleteMapping("/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();}

    //Atualizar um usuario com id utilizando PathVariable
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar (@Valid @PathVariable Long id, @RequestBody UsuarioUpdateDTO dto) {
        UsuarioDTO usuario = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(usuario);}
}
