package com.exemplo.usuariosapi.controller;
import com.exemplo.usuariosapi.dto.UsuarioCreateDTO;
import com.exemplo.usuariosapi.dto.UsuarioDTO;
import com.exemplo.usuariosapi.dto.UsuarioUpdateDTO;
import com.exemplo.usuariosapi.model.Usuario;
import com.exemplo.usuariosapi.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/usuarios")
public class UsuarioController { private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){this.usuarioService = usuarioService;}

    @GetMapping public List<UsuarioDTO> listar() {return usuarioService.listar();}

    @GetMapping("/{id}") public Usuario buscarPorId(@PathVariable Long id){return usuarioService.buscarPorId(id);}

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioCreateDTO dto){
        UsuarioDTO usuario = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(201).body(usuario);
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar (@PathVariable Long id, @RequestBody UsuarioUpdateDTO dto) {
        UsuarioDTO usuario = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(usuario);
    }
}
