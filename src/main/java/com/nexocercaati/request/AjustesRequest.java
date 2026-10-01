package com.nexocercaati.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record AjustesRequest(@NotEmpty List<@Valid AjusteValorRequest> ajustes) {
}
