package TREVO.api.controller;

import TREVO.api.company.Company;
import TREVO.api.order.Order;
import TREVO.api.repository.CompanyRepository;
import TREVO.api.DTOs.CompanyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;
    @PostMapping
    public ResponseEntity<?>  cadastrar(@RequestBody CompanyDTO dados){
        Company newcompany = new Company(dados);
        repository.save(newcompany);
        return ResponseEntity.ok().body(newcompany);
    }
    @GetMapping
    public Page<Company> listar(@PageableDefault() Pageable paginacao) {
        //return repository.findAll(paginacao);
        //Retorna apenas registros ativos:
        //return  repository.findAllByAtivoTrue(paginacao);
        //Retorna todos registros:
        return  repository.findAll(paginacao);
    }
    //Id dinâmico como parâmetro que passaremos na URL do insomnia
    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid CompanyDTO dados, @PathVariable Long id){
        Company company= repository.getReferenceById(id);
        assert company != null;
        company.atualizar(dados);
        repository.save(company);
        return ResponseEntity.ok().body(company);
    }
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id){
        //Exclui definitivamente:
        repository.deleteById(id);
        ObjectMapper mapper = new ObjectMapper();
        Company companyAtivo = new Company();
        return ResponseEntity.ok().body(companyAtivo);

        //Exclusão lógica, mantem arquivado:
        // Company company = repository.getReferenceById(id);
        //company.excluir();
        //repository.save(company);
        //return ResponseEntity.ok().body(companyAtivo);
        //Exclui definitivamente:
        //repository.deleteById(id);
    }
}
