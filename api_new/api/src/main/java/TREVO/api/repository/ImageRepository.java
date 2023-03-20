package TREVO.api.repository;

import TREVO.api.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByIdIn(List<Long> imgsIds);


}
