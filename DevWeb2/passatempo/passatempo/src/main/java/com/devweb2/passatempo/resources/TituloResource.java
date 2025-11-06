package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.dto.TituloCreateDTO;
import com.devweb2.passatempo.dto.TituloResponseDTO;
import com.devweb2.passatempo.service.TituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NOVO: Resource para Títulos.
 * Note o uso de DTOs diferentes para Criação (CreateDTO) e Resposta (ResponseDTO).
 */
@RestController
@RequestMapping("/api/titulos")
public class TituloResource {

    @Autowired
    private TituloService tituloService;

    @GetMapping
    public ResponseEntity<List<TituloResponseDTO>> listarTitulos() {
        List<TituloResponseDTO> lista = tituloService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TituloResponseDTO> buscarPeloId(@PathVariable Long id) {
        TituloResponseDTO dto = tituloService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TituloResponseDTO> criarTitulo(@RequestBody TituloCreateDTO createDTO) {
        TituloResponseDTO novoDto = tituloService.create(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TituloResponseDTO> atualizarTitulo(@PathVariable Long id, @RequestBody TituloCreateDTO createDTO) {
        TituloResponseDTO dtoAtualizado = tituloService.update(id, createDTO);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTitulo(@PathVariable Long id) {
        tituloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}