package com.nexocercaati.request;

import com.nexocercaati.model.TipoDocumento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Datos personales que el usuario puede editar de su propio perfil. El nombre de usuario, el rol y
 * el estado de la cuenta no se cambian desde aquí. Los campos opcionales vacíos se guardan como nulo.
 */
public record PerfilRequest(
        @NotBlank @Size(max = 120) String nombres,
        @NotBlank @Size(max = 120) String apellidos,
        TipoDocumento tipoDocumento,
        @Size(max = 20) @Pattern(regexp = ValidacionPatrones.NUMERO_DOCUMENTO) String numeroDocumento,
        @Size(max = 160) @Email String email,
        @Size(max = 30) @Pattern(regexp = ValidacionPatrones.TELEFONO) String telefono) {
}
