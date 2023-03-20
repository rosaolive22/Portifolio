package TREVO.api.DTOs;

import TREVO.api.catalog.Culture;

public record CatalogDTO(
        Culture culture,
        String linha) {
}
