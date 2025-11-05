package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.domain.Classe;
import com.devweb2.passatempo.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClasseResource {

    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping
    public List<Classe> listarClasses() {
        return classeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> buscarPeloId(@PathVariable Long id) {
        Optional<Classe> classe = classeRepository.findById(id);
        return classe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Classe criarClasse(@RequestBody Classe classe) {
        return classeRepository.save(classe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> atualizarClasse(@PathVariable Long id, @RequestBody Classe classeDetalhes) {
        return classeRepository.findById(id)
                .map(classe -> {
                    classe.setNome(classeDetalhes.getNome());
                    classe.setValor(classeDetalhes.getValor());
                    classe.setPrazoDevolucao(classeDetalhes.getPrazoDevolucao());
                    Classe classeAtualizada = classeRepository.save(classe);
                    return ResponseEntity.ok(classeAtualizada);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClasse(@PathVariable Long id) {
        return classeRepository.findById(id)
                .map(classe -> {
                    classeRepository.delete(classe);
                    return ResponseEntity.noContent().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}