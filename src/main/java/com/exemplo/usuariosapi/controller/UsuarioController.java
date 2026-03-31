package com.exemplo.usuariosapi.controller;
import com.exemplo.usuariosapi.model.Usuario;
import com.exemplo.usuariosapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario buscarPorId(@PathVariable Long id){return service.buscarPorId(id);}

    @PostMapping("/usuarios")
    public Usuario salvar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario atualizar (@PathVariable Long id, @RequestBody Usuario usuario){return service.atualizar(id, usuario);}
}
