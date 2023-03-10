package TREVO.api.product;

import TREVO.api.catalog.Culture;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public record ProductDTO(
        @NotBlank
        String name,
        @NotBlank
        String size,
        @NotNull
        Boolean status,
        Culture culture,
        @JsonProperty ("imgs")
        @NotEmpty(message = "Adicionar imagem.")
        List<Long>imgsIds){
        }


