package TREVO.api.controller;

import TREVO.api.domain.Catalog;
import TREVO.api.repository.CatalogRepository;
import TREVO.api.DTOs.CatalogDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("catalog")

public class CatalogController {
    @Autowired
    private CatalogRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CatalogDTO dados){
        Catalog newcatalog = new Catalog(dados);
        repository.save(newcatalog);
        return ResponseEntity.ok().body(newcatalog);
    }
    @GetMapping
    public Page<Catalog> listar(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable paginacao) {
        //Retorna apenas registros ativos
        //return  repository.findAllByAtivoTrue(paginacao);
        //Retorna todos registros
        return  repository.findAll(paginacao);
    }
    //Id dinâmico como parâmetro que passaremos na URL do insomnia
    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid CatalogDTO dados, @PathVariable Long id){
        Catalog catalog= repository.findById(id).orElse(null);
        assert catalog != null;
        catalog.atualizar(dados);
        repository.save(catalog);
        return ResponseEntity.ok().body(catalog);
    }
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id){
        //Exclui definitivamente:
        repository.deleteById(id);
        ObjectMapper mapper = new ObjectMapper();
        Catalog catalogAtivo = new Catalog();
        return ResponseEntity.ok().body(catalogAtivo);

        //Exclusão lógica, mantem arquivado:
        //Catalog catalog= repository.findById(id).orElse(null);
        //assert catalog != null;
        //catalog.excluir();
        //repository.save(catalog);
        //return ResponseEntity.ok().body(catalogAtivo);
        //Exclui definitivamente:
        //repository.deleteById(id);
    }
}
