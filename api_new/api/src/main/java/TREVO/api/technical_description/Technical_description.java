package TREVO.api.technical_description;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "tb_technical")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cod")

public class Technical_description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod;
    @NotBlank
    @Column(name = "indicated",columnDefinition = "Text")
    private String indicated_technical;
    private Boolean ativo;
    public Technical_description(DadosTechnical dados) {
        this.cod = dados.cod();
        this.indicated_technical = dados.indicated_technical();
        this.ativo = true;
    }
    public void atualizar(DadosTechnical dados) {
        if (dados.indicated_technical() != null) {
            this.indicated_technical = dados.indicated_technical();
        }
    }
    public void excluir() {
        this.ativo = false;
    }
}
