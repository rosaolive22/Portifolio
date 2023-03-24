package TREVO.api.domain;

import TREVO.api.DTOs.OrderDTO;
import TREVO.api.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Table(name = "tb_order")//passar porq o nome da table é diferente da Entidade
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id") <--quando o nome da coluna for diferente
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private String phone;
    @NotBlank
    @Column(name = "e_mail",unique = true)
    private String e_mail;
    @NotBlank
    private String country;
    private Boolean ativo;

    // Relacionamentos
    @ManyToMany
    @JoinTable
            //Nome da table de relação/N coluna/identificador desta tabela/tables 2ª
            (
                    name = "order_product",
                    joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
            )
    private List<Product> products;

    public Order(OrderDTO dados, List<Product> products) {
        this.name = dados.name();
        this.phone = dados.phone();
        this.e_mail = dados.e_mail();
        this.country = dados.country();
        this.products = products;
        this.ativo = true;
    }

    public void atualizar(OrderDTO dados, List<Product> products) {
        if (dados.name() != null) {
            this.name = dados.name();
        }
        if (dados.phone() != null) {
            this.phone = dados.phone();
        }
        if (dados.e_mail() != null) {
            this.e_mail = dados.e_mail();
        }
        if (dados.country() != null) {
            this.country = dados.country();
        }
        if (products != null){
            this.products = products;
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
