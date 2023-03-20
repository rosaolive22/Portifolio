package TREVO.api.repository;

import TREVO.api.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIdIn(List<Long> productIds);
    //Page<Product> findAllByAtivoTrue(Pageable paginacao);

}
