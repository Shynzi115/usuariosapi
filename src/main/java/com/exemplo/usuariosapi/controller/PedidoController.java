package com.exemplo.usuariosapi.controller;

import com.exemplo.usuariosapi.dto.PedidoCreateDTO;
import com.exemplo.usuariosapi.dto.PedidoStatusDTO;
import com.exemplo.usuariosapi.model.Pedido;
import com.exemplo.usuariosapi.repository.PedidoRepository;
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
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoController pedidoController, PedidoService pedidoService, PedidoRepository pedidoRepository){
        this.pedidoService = pedidoService;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> criar (@RequestBody PedidoCreateDTO dto){
        Pedido pedido = pedidoService.criar(dto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping
    public Page<Pedido> listar(@RequestParam(required = false) Long usuarioId, Pageable pageable) {
        return pedidoService.listar(usuarioId, pageable);
    }

    @PatchMapping("/{id}/status")
    public  Pedido atualizarStatus(@PathVariable Long id, @RequestBody PedidoStatusDTO dto){
        return pedidoService.atualizarStatus(id, dto.getStatus());
    }
}
