package com.devweb2.passatempo.resources;

import com.devweb2.passatempo.dto.DevolucaoDTO;
import com.devweb2.passatempo.dto.LocacaoDTO;
import com.devweb2.passatempo.service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locacoes")
public class LocacaoResource {

    @Autowired
    private LocacaoService locacaoService;

    @GetMapping
    public ResponseEntity<List<LocacaoDTO>> listar() {
        return ResponseEntity.ok(locacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocacaoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(locacaoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LocacaoDTO> criar(@RequestBody LocacaoDTO dto) {
        LocacaoDTO novoDto = locacaoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocacaoDTO> atualizar(@PathVariable Long id, @RequestBody LocacaoDTO dto) {
        return ResponseEntity.ok(locacaoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        locacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/devolucao")
    public ResponseEntity<LocacaoDTO> realizarDevolucao(@RequestBody DevolucaoDTO devolucaoDTO) {
        LocacaoDTO locacaoAtualizada = locacaoService.realizarDevolucao(devolucaoDTO);
        return ResponseEntity.ok(locacaoAtualizada);
    }
}