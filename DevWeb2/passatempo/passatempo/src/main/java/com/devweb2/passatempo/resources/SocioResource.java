package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.dto.SocioDTO;
import com.devweb2.passatempo.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NOVO: Resource para Sócios.
 */
@RestController
@RequestMapping("/api/socios")
public class SocioResource {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public ResponseEntity<List<SocioDTO>> listarSocios() {
        List<SocioDTO> lista = socioService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocioDTO> buscarPeloId(@PathVariable Long id) {
        SocioDTO dto = socioService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SocioDTO> criarSocio(@RequestBody SocioDTO socioDTO) {
        SocioDTO novoDto = socioService.create(socioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioDTO> atualizarSocio(@PathVariable Long id, @RequestBody SocioDTO socioDTO) {
        SocioDTO dtoAtualizado = socioService.update(id, socioDTO);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSocio(@PathVariable Long id) {
        socioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}