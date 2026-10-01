package com.nexocercaati.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/** Ids en el orden deseado: la posición en la lista pasa a ser el nuevo orden. */
public record OrdenRequest(@NotEmpty List<@NotNull Long> ids) {
}
