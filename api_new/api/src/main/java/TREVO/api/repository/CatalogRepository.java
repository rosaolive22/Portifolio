package TREVO.api.repository;

import TREVO.api.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
    List<Catalog> findByIdIn (List<Long>catalogsIds);

}
