package TREVO.api.controller;

import TREVO.api.domain.Company;
import TREVO.api.repository.CompanyRepository;
import TREVO.api.DTOs.CompanyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;

    //Retorno201+corpo+cabeçalho do protocolo HTTP
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CompanyDTO dados, UriComponentsBuilder uriBuilder){
        var company = new Company(dados);
        //Company newcompany = new Company(dados);
        repository.save(company);
        var uri = uriBuilder.path("/company/{id}").buildAndExpand(company.getId()).toUri();
        return ResponseEntity.created(uri).body(company);
    }
    //Retorno200
    @GetMapping
    public ResponseEntity<?> listar(@PageableDefault() Pageable paginacao) {
        var page = repository.findAll(paginacao);
        if (page.isEmpty()){
            return ResponseEntity.badRequest().body("Lista vazia.");
        }
        return ResponseEntity.ok(page);
    }
    //Retorno200
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid CompanyDTO dados, @PathVariable Long id) {
        var company = repository.findById(id).orElse(null);
        assert company != null;
        company.atualizar(dados);
        repository.save(company);
        return ResponseEntity.ok().body(company);
    }
    //Retorno204
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        //Exclui definitivamente:
        repository.deleteById(id);
        ObjectMapper mapper = new ObjectMapper();
        Company companyAtivo = new Company();
        return ResponseEntity.noContent().build();
    }
    //Retorno200
    @GetMapping("/{id}")
        public ResponseEntity<?> detalhar(@PathVariable Long id){
        //var company = new Company(dados).repository.getReferenceById(id);
        var company  = repository.getReferenceById(id);//findById(id);
        return ResponseEntity.ok(company);
    }
}
