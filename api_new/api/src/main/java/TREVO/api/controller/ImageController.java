package TREVO.api.controller;

import TREVO.api.image.Image;
import TREVO.api.image.ImageRepository;
import TREVO.api.image.ImageDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("image")
public class ImageController {
    @Autowired
    private ImageRepository repository;
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody ImageDTO dados){
        repository.save(new Image(dados));
        return ResponseEntity.ok().body(" Imagem cadastrada!");
    }
    @GetMapping(value = "/listar")
    public Page<Image> listar(@PageableDefault(page = 0, size = 10, sort = {"img"}) Pageable paginacao) {
        return repository.findAll(paginacao);
    }
    //Id(cod) dinâmico como parâmetro que passaremos na URL do insomnia
    @PutMapping(value = "/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid ImageDTO dados, @PathVariable Long id){
        Image image= repository.findById(id).orElse(null);
        assert image != null;
        image.atualizar(dados);
        repository.save(image);
        return ResponseEntity.ok().body("Imagem atualizada com sucesso!");
    }
    @DeleteMapping(value = "excluir/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id){
        //Exclusão lógica, mantem arquivado:
        Image image= repository.findById(id).orElse(null);
        assert image != null;
        image.excluir();
        return ResponseEntity.ok().body("Exclusão lógica concluida.");
        //Exclui definitivamente:
        //repository.deleteById(cod);
    }
}
