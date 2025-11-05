package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.domain.Ator;
import com.devweb2.passatempo.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atores")
public class AtorResource {

    @Autowired
    private AtorRepository atorRepository;

    @GetMapping
    public List<Ator> listarAtores() {
        return atorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> buscarPeloId(@PathVariable Long id) {
        Optional<Ator> ator = atorRepository.findById(id);
        return ator.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ator criarAtor(@RequestBody Ator ator) {
        return atorRepository.save(ator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ator> atualizarAtor(@PathVariable Long id, @RequestBody Ator atorDetalhes) {
        return atorRepository.findById(id)
                .map(ator -> {
                    ator.setNome(atorDetalhes.getNome());
                    Ator atorAtualizado = atorRepository.save(ator);
                    return ResponseEntity.ok(atorAtualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtor(@PathVariable Long id) {
        return atorRepository.findById(id)
                .map(ator -> {
                    atorRepository.delete(ator);
                    return ResponseEntity.noContent().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}