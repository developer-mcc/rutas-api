package com.nexocercaati.exception;

import com.nexocercaati.response.ApiErrorResponse;
import java.time.Instant;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String MENSAJE_INTERNO = "Ocurrió un error inesperado";

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> noEncontrado(RecursoNoEncontradoException ex) {
        return responder(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());
    }

    @ExceptionHandler(SolicitudInvalidaException.class)
    public ResponseEntity<ApiErrorResponse> solicitudInvalida(SolicitudInvalidaException ex) {
        return responder(HttpStatus.BAD_REQUEST, ex.getMessage(), List.of());
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<ApiErrorResponse> duplicado(RecursoDuplicadoException ex) {
        return responder(HttpStatus.CONFLICT, ex.getMessage(), List.of());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiErrorResponse> archivoGrande() {
        return responder(HttpStatus.PAYLOAD_TOO_LARGE, "El archivo supera el tamaño máximo permitido", List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> validacion(MethodArgumentNotValidException ex) {
        List<String> detalles = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        return responder(HttpStatus.BAD_REQUEST, "Datos de entrada inválidos", detalles);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> cuerpoIlegible() {
        return responder(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no es válido", List.of());
    }

    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<ApiErrorResponse> credenciales(CredencialesInvalidasException ex) {
        return responder(HttpStatus.UNAUTHORIZED, ex.getMessage(), List.of());
    }

    @ExceptionHandler(CuentaBloqueadaException.class)
    public ResponseEntity<ApiErrorResponse> bloqueada(CuentaBloqueadaException ex) {
        return responder(HttpStatus.TOO_MANY_REQUESTS, ex.getMessage(), List.of());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> accesoDenegado() {
        return responder(HttpStatus.FORBIDDEN, "No tienes permiso para esta operación", List.of());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> inesperado(Exception ex) {
        log.error("Error no controlado", ex);
        return responder(HttpStatus.INTERNAL_SERVER_ERROR, MENSAJE_INTERNO, List.of());
    }

    private ResponseEntity<ApiErrorResponse> responder(
            HttpStatus estado, String mensaje, List<String> detalles) {
        var cuerpo = new ApiErrorResponse(
                Instant.now(), estado.value(), estado.getReasonPhrase(), mensaje, detalles);
        return ResponseEntity.status(estado).body(cuerpo);
    }
}
