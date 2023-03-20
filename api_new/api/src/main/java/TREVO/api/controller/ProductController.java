package TREVO.api.controller;

import TREVO.api.catalog.Catalog;
import TREVO.api.repository.CatalogRepository;
import TREVO.api.image.Image;
import TREVO.api.repository.ImageRepository;
import TREVO.api.product.Product;
import TREVO.api.DTOs.ProductDTO;//
import TREVO.api.repository.ProductRepository;//
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;//
import jakarta.validation.Valid;//
import org.springframework.beans.factory.annotation.Autowired;//
import org.springframework.data.domain.Page;//
import org.springframework.data.domain.Pageable;//
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;//
import org.springframework.web.bind.annotation.*;//

import java.util.List;


@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CatalogRepository catalogRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProductDTO dados) {
        List<Image> imgs = imageRepository.findByIdIn(dados.imgsIds());
        List<Catalog> catalogs = catalogRepository.findByIdIn(dados.catalogIds());
        Product newproduct = new Product(dados, imgs, catalogs);
        repository.save(newproduct);
        return ResponseEntity.ok().body(newproduct);// devolve em front end

        //var uri :URI = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
        //return ResponseEntity.created(uri).body(new ProductDTO (product));
    }
    @GetMapping//não precisa-->(value = "/listar")
    public Page<Product> listar(@PageableDefault(size=10, sort={"name"}) Pageable paginacao){
        //Retorna apenas registros ativos
        //return  repository.findAllByAtivoTrue(paginacao);

        //Retorna todos registros
         return  repository.findAll(paginacao);
    }
    //Id dinâmico como parâmetro que passaremos na URL do insomnia
    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid ProductDTO dados, @PathVariable Long id){
        Product product= repository.findById(id).orElse(null);
        List<Image> imgs = imageRepository.findByIdIn(dados.imgsIds());
        List<Catalog> catalogs = catalogRepository.findByIdIn(dados.catalogIds());
        assert product != null;
        product.atualizar(dados,imgs, catalogs);
        repository.save(product);
        return ResponseEntity.ok().body(product);
    }
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id){
        //Excluir definitivamente:
        repository.deleteById(id);
        //repository.product
        ObjectMapper mapper = new ObjectMapper();
        Product productAtivo = new Product();

       //Exclusão lógica, mantem arquivado:
        //Product product = repository.findById(id).orElse(null);
        //assert product != null;
        //product.excluir();
        return ResponseEntity.ok().body(productAtivo);

    }
}
