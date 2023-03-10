package TREVO.api.sale;

import jakarta.validation.constraints.NotBlank;

public record DadosSale(
        @NotBlank
        String information,
        Integer id_product) {
}
