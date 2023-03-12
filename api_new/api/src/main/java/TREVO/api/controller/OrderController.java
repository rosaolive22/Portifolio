package TREVO.api.controller;

import TREVO.api.order.Order;
import TREVO.api.order.OrderRepository;
import TREVO.api.order.OrderDTO;
import TREVO.api.product.Product;
import TREVO.api.product.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @PostMapping
    public void order(@RequestBody OrderDTO dados) {
        List<Product> products = productRepository.findByIdIn(dados.productIds());
        repository.save(new Order(dados, products));
    }
    @GetMapping(value ="/listar")
    public Page<Order> listar(Pageable paginacao){
        return repository.findAll(paginacao);
    }

    //Id dinâmico como parâmetro que passaremos na URL do insomnia
    @PutMapping(value = "/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid OrderDTO dados, @PathVariable Long id){
        Order order = repository.findById(id).orElse(null);
        assert order != null;
        order.atualizar(dados);
        repository.save(order);
        return ResponseEntity.ok().body("Solicitação de orçamento efetuada com sucesso! \nEm breve um de nossos vendedores entrará em contato.  \nEquipe TREVO S.A. agradece! ");
    }
    @DeleteMapping(value = "excluir/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        //Exclusão lógica, mantem arquivado:
        Order order = repository.findById(id).orElse(null);
        assert order != null;
        order.excluir();
        repository.save(order);
        //Exclui definitivamente:
        //repository.deleteById(order);
    }
}
