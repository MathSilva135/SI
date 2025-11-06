package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.dto.DependenteDTO;
import com.devweb2.passatempo.service.DependenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NOVO: Resource para Dependentes.
 */
@RestController
@RequestMapping("/api/dependentes")
public class DependenteResource {

    @Autowired
    private DependenteService dependenteService;

    @GetMapping
    public ResponseEntity<List<DependenteDTO>> listarDependentes() {
        List<DependenteDTO> lista = dependenteService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DependenteDTO> buscarPeloId(@PathVariable Long id) {
        DependenteDTO dto = dependenteService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DependenteDTO> criarDependente(@RequestBody DependenteDTO dependenteDTO) {
        DependenteDTO novoDto = dependenteService.create(dependenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DependenteDTO> atualizarDependente(@PathVariable Long id, @RequestBody DependenteDTO dependenteDTO) {
        DependenteDTO dtoAtualizado = dependenteService.update(id, dependenteDTO);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDependente(@PathVariable Long id) {
        dependenteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}