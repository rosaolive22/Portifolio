package TREVO.api.DTOs;

import TREVO.api.Enum.Culture;
import jakarta.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public record ProductDTO(
        //@NotBlank
        //@Column(name = "name",unique = true)
        String name,
        //@NotBlank
        String size,
        //@NotNull
        Boolean status,
        //@NotBlank
        String description,
        Culture culture,
        @JsonProperty ("imgs")
        @NotEmpty(message = "Adicionar imagem.")
        List<Long>imgsIds,
        @JsonProperty ("catalogs")
        @NotEmpty(message = "Adicionar catalog.")
        List<Long>catalogIds){
        }


