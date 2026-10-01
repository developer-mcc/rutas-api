package com.nexocercaati.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CambioPasswordRequest(
        @NotBlank @Size(max = 128) String actual,
        @NotBlank @Size(min = 12, max = 128) String nueva) {
}
