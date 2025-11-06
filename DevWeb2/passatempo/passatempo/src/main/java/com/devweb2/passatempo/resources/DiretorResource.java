package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.dto.DiretorDTO;
import com.devweb2.passatempo.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REFATORADO: Agora usa DiretorService e DiretorDTO.
 */
@RestController
@RequestMapping("/api/diretores")
public class DiretorResource {

    @Autowired
    private DiretorService diretorService; // Injeta o Service

    @GetMapping
    public ResponseEntity<List<DiretorDTO>> listarDiretores() {
        List<DiretorDTO> lista = diretorService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiretorDTO> buscarPeloId(@PathVariable Long id) {
        DiretorDTO dto = diretorService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DiretorDTO> criarDiretor(@RequestBody DiretorDTO diretorDTO) {
        DiretorDTO novoDto = diretorService.create(diretorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiretorDTO> atualizarDiretor(@PathVariable Long id, @RequestBody DiretorDTO diretorDTO) {
        DiretorDTO dtoAtualizado = diretorService.update(id, diretorDTO);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable Long id) {
        diretorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}