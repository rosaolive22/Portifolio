package TREVO.api.catalog;

import TREVO.api.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
    List<Catalog> findByIdIn (List<Long>catalogsIds);

}
