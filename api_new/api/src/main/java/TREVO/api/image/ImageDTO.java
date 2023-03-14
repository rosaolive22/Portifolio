package TREVO.api.image;

import jakarta.validation.constraints.NotNull;

import java.net.URL;

public record ImageDTO(
        URL img) {
}
