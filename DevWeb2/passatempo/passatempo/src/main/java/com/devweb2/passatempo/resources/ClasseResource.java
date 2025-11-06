package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.dto.ClasseDTO;
import com.devweb2.passatempo.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REFATORADO: Agora usa ClasseService e ClasseDTO.
 */
@RestController
@RequestMapping("/api/classes")
public class ClasseResource {

    @Autowired
    private ClasseService classeService; // Injeta o Service

    @GetMapping
    public ResponseEntity<List<ClasseDTO>> listarClasses() {
        List<ClasseDTO> lista = classeService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDTO> buscarPeloId(@PathVariable Long id) {
        ClasseDTO dto = classeService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClasseDTO> criarClasse(@RequestBody ClasseDTO classeDTO) {
        ClasseDTO novoDto = classeService.create(classeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasseDTO> atualizarClasse(@PathVariable Long id, @RequestBody ClasseDTO classeDTO) {
        ClasseDTO dtoAtualizado = classeService.update(id, classeDTO);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClasse(@PathVariable Long id) {
        classeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}