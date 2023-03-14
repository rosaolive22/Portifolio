package TREVO.api.controller;

import TREVO.api.catalog.Catalog;
import TREVO.api.catalog.CatalogRepository;
import TREVO.api.catalog.CatalogDTO;
import TREVO.api.product.Product;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.core.convert.TypeDescriptor.map;

@RestController
@RequestMapping("catalog")

public class CatalogController {
    @Autowired
    private CatalogRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CatalogDTO dados){
        repository.save(new Catalog(dados));
        return ResponseEntity.ok().body("Catalogo cadastrado com sucesso!");
    }
    @GetMapping(value ="/listar")
    public Page<Catalog> listar(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable paginacao) {
        //Retorna apenas registros ativos
        //return  repository.findAllByAtivoTrue(paginacao);
        //Retorna todos registros
        return  repository.findAll(paginacao);
    }
    //Id dinâmico como parâmetro que passaremos na URL do insomnia
    @PutMapping(value = "/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid CatalogDTO dados, @PathVariable Long id){
        Catalog catalog= repository.findById(id).orElse(null);
        assert catalog != null;
        catalog.atualizar(dados);
        repository.save(catalog);
        return ResponseEntity.ok().body("Catalogo atualizado com sucesso!");
    }
    @DeleteMapping(value = "excluir/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id){
        //Exclui definitivamente:
        repository.deleteById(id);

        //Exclusão lógica, mantem arquivado:
        //Catalog catalog= repository.findById(id).orElse(null);
        //assert catalog != null;
        //catalog.excluir();
        //repository.save(catalog);
        return ResponseEntity.ok().body("Catalogo excluído com sucesso.");
        //Exclui definitivamente:
        //repository.deleteById(id);
    }
}
