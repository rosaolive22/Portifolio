package TREVO.api.catalog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "tb_catalog")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")


public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Culture culture;
    private Integer id_company;
    private Boolean ativo;

    public Catalog(CatalogDTO dados) {
        this.culture = dados.culture();
        this.ativo = true;
    }
    public void atualizar(CatalogDTO dados) {
        if(dados.culture() != null){
            this.culture = dados.culture();
        }
    }
    public void excluir() {
        this.ativo = false;
    }
}

