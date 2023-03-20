package TREVO.api.controller;

import TREVO.api.order.Order;
import TREVO.api.repository.OrderRepository;
import TREVO.api.DTOs.OrderDTO;
import TREVO.api.product.Product;
import TREVO.api.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<?> cadastrar(@RequestBody OrderDTO dados) {
        List<Product> products = productRepository.findByIdIn(dados.productIds());
        Order neworder = new Order(dados, products);
        repository.save(neworder);
        return ResponseEntity.ok().body(neworder);
        //repository.save(new Order(dados, products));

        //return ResponseEntity.ok().body("Solicitação de orçamento efetuada com sucesso! " +
                //"\nEm breve um de nossos vendedores entrará em contato.  \nEquipe TREVO S.A. agradece! ");
    }
    @GetMapping
    public Page<Order> listar(Pageable paginacao){
        return repository.findAll(paginacao);
    }

    //Id dinâmico como parâmetro que passaremos na URL do insomnia
    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid OrderDTO dados, @PathVariable Long id){
        Order order = repository.findById(id).orElse(null);
        List<Product> products = productRepository.findByIdIn(dados.productIds());
        assert order != null;
        order.atualizar(dados, products);
        repository.save(order);
        return ResponseEntity.ok().body(order);

        //return ResponseEntity.ok().body("Solicitação de orçamento atualizada com sucesso!" +
               // " \nEm breve um de nossos vendedores entrará em contato.  \nEquipe TREVO S.A. agradece! ");
    }
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id){
        //Exclui definitivamente:
        repository.deleteById(id);
        ObjectMapper mapper = new ObjectMapper();
        Order orderAtivo = new Order();
        return ResponseEntity.ok().body(orderAtivo);
        //Exclusão lógica, mantem arquivado:
        //Order order = repository.findById(id).orElse(null);
        //assert order != null;
        //order.excluir();
        //repository.save(order);

        //return ResponseEntity.ok().body(orderAtivo);

    }
}
