package com.exemplo.usuariosapi.controller;
import com.exemplo.usuariosapi.dto.UsuarioCreateDTO;
import com.exemplo.usuariosapi.dto.UsuarioResponseDTO;
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
    public Page<UsuarioResponseDTO> listar(@RequestParam(required = false) String nome, Pageable pageable) {
        return usuarioService.listar(nome, pageable);}

    //Busca de usuarios com GET e PathVariable para buscar por id
    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);}

    //Post para inserir usuarios
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioCreateDTO dto){
        UsuarioResponseDTO usuario = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(201).body(usuario);}

    //Deletar usuarios com DELETE
    @DeleteMapping("/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();}

    //Atualizar um usuario com id utilizando PathVariable
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar (@Valid @PathVariable Long id, @RequestBody UsuarioResponseDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(usuario);}
}
