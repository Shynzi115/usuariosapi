package com.exemplo.usuariosapi.controller;

import com.exemplo.usuariosapi.dto.PedidoCreateDTO;
import com.exemplo.usuariosapi.model.Pedido;
import com.exemplo.usuariosapi.model.Usuario;
import com.exemplo.usuariosapi.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoController pedidoController, PedidoService pedidoService){
        this.pedidoService = pedidoService;

    }

    @PostMapping
    public ResponseEntity<Pedido> criar (@RequestBody PedidoCreateDTO dto){
        Pedido pedido = pedidoService.criar(dto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping("/usuario/{id}")
    public Page<Pedido> listarPorUsuario(@PathVariable Long id, Pageable pageable){
        return pedidoService.listarPorUsuario(id, pageable);
    }

    @GetMapping
    public Page<Pedido> listar(@RequestParam(required = false) Long usuarioId, Pageable pageable) {
        return pedidoService.listar(usuarioId, pageable);
    }
}
