package TREVO.api.domain; //pacote de coleção de classes

import TREVO.api.DTOs.ProductDTO;
import TREVO.api.Enum.Culture;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Table(name = "tb_product")
@Entity(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Column(name = "name",unique = true)
    private String name;
    @Column(name = "size")
    private String size;
    @NotNull
    @Column(name = "status")
    private Boolean status;
    @NotBlank
    @Column(name = "description", columnDefinition = "Text")
    private String description;
    @Column(name = "date_register")
    private LocalDate date_register = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Culture culture;
    private Boolean ativo;
    @OneToMany
    @JoinTable
            (
                    name = "product_image",
                    joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "URL_img", referencedColumnName = "id")}
            )
    private List<Image>imgs;
    @ManyToMany
    @JoinTable
            (
                    name = "product_catalog",
                    joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "catalog_id", referencedColumnName = "id")}
            )
    private List<Catalog>catalogs;

    public Product(ProductDTO dados, List<Image>imgs, List<Catalog>catalogs) {
        this.name = dados.name();
        this.size = dados.size();
        this.status = dados.status();
        this.culture = dados.culture();
        this.imgs = imgs;
        this.catalogs = catalogs;
        this.description = dados.description();
        this.ativo = true;
    }
    public void atualizar(ProductDTO dados, List<Image>imgs, List<Catalog>catalogs) {
        if (dados.name() != null) {
            this.name = dados.name();
        }
        if (dados.size() != null) {
            this.size = dados.size();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
        if (imgs != null){
            this.imgs = imgs;
        }
        if (catalogs != null){
            this.catalogs = catalogs;
        }
        if (dados.culture() != null) {
            this.culture = dados.culture();
        }
        if (dados.description() != null){
            this.description =dados.description();
        }
    }
    public void excluir() {
        this.ativo = false;
    }
}
