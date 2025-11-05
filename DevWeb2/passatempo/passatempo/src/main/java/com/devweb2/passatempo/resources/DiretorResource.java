package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.domain.Diretor;
import com.devweb2.passatempo.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diretores")
public class DiretorResource {

    @Autowired
    private DiretorRepository diretorRepository;

    @GetMapping
    public List<Diretor> listarDiretores() {
        return diretorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> buscarPeloId(@PathVariable Long id) {
        Optional<Diretor> diretor = diretorRepository.findById(id);
        return diretor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Diretor criarDiretor(@RequestBody Diretor diretor) {
        return diretorRepository.save(diretor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizarDiretor(@PathVariable Long id, @RequestBody Diretor diretorDetalhes) {
        return diretorRepository.findById(id)
                .map(diretor -> {
                    diretor.setNome(diretorDetalhes.getNome());
                    Diretor diretorAtualizado = diretorRepository.save(diretor);
                    return ResponseEntity.ok(diretorAtualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable Long id) {
        return diretorRepository.findById(id)
                .map(diretor -> {
                    diretorRepository.delete(diretor);
                    return ResponseEntity.noContent().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}