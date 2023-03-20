package TREVO.api.DTOs;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record OrderDTO(
        @NotBlank
        String name,
        @NotBlank
        String phone,
        @NotBlank
        //@Column(name = "e_mail")
        String e_mail,
        @NotBlank
        String country,
        @JsonProperty ("products")
        List<Long>productIds){
}

