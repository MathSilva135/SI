package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.dto.AtorDTO;
import com.devweb2.passatempo.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atores")
public class AtorResource {

    // MUDANÇA: Injeta o AtorService, não o AtorRepository
    @Autowired
    private AtorService atorService;

    @GetMapping
    public ResponseEntity<List<AtorDTO>> listarAtores() {
        // A lógica de negócio foi para o Service
        List<AtorDTO> lista = atorService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtorDTO> buscarPeloId(@PathVariable Long id) {
        // O Service agora cuida de tudo.
        // Se não encontrar, ele lançará a ResourceNotFoundException,
        // que será capturada automaticamente pelo RestExceptionHandler.
        AtorDTO dto = atorService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AtorDTO> criarAtor(@RequestBody AtorDTO atorDTO) {
        AtorDTO novoDto = atorService.create(atorDTO);
        // Retorna o status 201 Created (melhor prática)
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtorDTO> atualizarAtor(@PathVariable Long id, @RequestBody AtorDTO atorDTO) {
        AtorDTO dtoAtualizado = atorService.update(id, atorDTO);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtor(@PathVariable Long id) {
        atorService.delete(id);
        // Retorna 204 No Content (melhor prática)
        return ResponseEntity.noContent().build();
    }
}