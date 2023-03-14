package TREVO.api.controller;

import TREVO.api.company.Company;
import TREVO.api.company.CompanyRepository;
import TREVO.api.company.CompanyDTO;
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
        repository.save(new Company(dados));
        return ResponseEntity.ok().body("Company cadastrada com sucesso!");
    }
    @GetMapping(value ="/listar")
    public Page<Company> listar(@PageableDefault() Pageable paginacao) {
        //return repository.findAll(paginacao);
        //Retorna apenas registros ativos:
        //return  repository.findAllByAtivoTrue(paginacao);
        //Retorna todos registros:
        return  repository.findAll(paginacao);
    }
    //Id dinâmico como parâmetro que passaremos na URL do insomnia
    @PutMapping(value = "/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid CompanyDTO dados, @PathVariable Long id){
        Company company= repository.getReferenceById(id);
        company.atualizar(dados);
        repository.save(company);
        return ResponseEntity.ok().body("Dados da Company TREVO S.A., atualizado com sucesso!");
    }
    @DeleteMapping(value = "excluir/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id){
        //Exclui definitivamente:
        repository.deleteById(id);

        //Exclusão lógica, mantem arquivado:
        // Company company = repository.getReferenceById(id);
        //company.excluir();
        //repository.save(company);
        return ResponseEntity.ok().body("Company, excluída.");
        //Exclui definitivamente:
        //repository.deleteById(id);
    }
}
