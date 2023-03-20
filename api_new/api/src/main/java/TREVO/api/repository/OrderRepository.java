package TREVO.api.repository;

import TREVO.api.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//    Order getReferenceById(Long id);

}
