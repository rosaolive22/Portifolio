package TREVO.api.DTOs;

import TREVO.api.Enum.Culture;

public record CatalogDTO(
        Culture culture,
        String linha) {
}
