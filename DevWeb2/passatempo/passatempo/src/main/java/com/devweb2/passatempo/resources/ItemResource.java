package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.dto.ItemDTO;
import com.devweb2.passatempo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NOVO: Resource para Itens.
 */
@RestController
@RequestMapping("/api/itens")
public class ItemResource {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> listarItens() {
        List<ItemDTO> lista = itemService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> buscarPeloId(@PathVariable Long id) {
        ItemDTO dto = itemService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> criarItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO novoDto = itemService.create(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> atualizarItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        ItemDTO dtoAtualizado = itemService.update(id, itemDTO);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}