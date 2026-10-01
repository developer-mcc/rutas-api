package com.nexocercaati.request;

/** Expresiones regulares compartidas por los request. */
public final class ValidacionPatrones {

    /** Vacío, una imagen propia (/uploads/archivo) o una URL https externa. */
    public static final String IMAGEN_URL = "^(|/uploads/[A-Za-z0-9._-]+|https://\\S+)$";

    public static final String SLUG = "^[a-z0-9]+(-[a-z0-9]+)*$";

    public static final String MONEDA = "^(PEN|USD)$";

    public static final String PASAJEROS = "^([1-4]|5\\+)$";

    /** Vacío o letras, números y guiones (4 a 20 caracteres). */
    public static final String NUMERO_DOCUMENTO = "^(|[A-Za-z0-9-]{4,20})$";

    /** Vacío o un teléfono con dígitos, espacios, guiones y paréntesis, con un + inicial opcional. */
    public static final String TELEFONO = "^(|\\+?\\d[\\d ()-]{5,29})$";

    private ValidacionPatrones() {
    }
}
