package mx.unam.dgtic.java.m8.ticketsapirest.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    // Manejo de JSON mal formado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errorDetalle = new HashMap<>();
        errorDetalle.put("mensaje", "El cuerpo está mal formado");
        errorDetalle.put("status", HttpStatus.BAD_REQUEST.toString());

        Throwable causa = ex.getMostSpecificCause();
        if (causa != null) {
            errorDetalle.put("cause", causa.getMessage());
        }

        return ResponseEntity.badRequest().body(errorDetalle);
    }

    // Manejo de validaciones fallidas en DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorDetalle = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorDetalle.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errorDetalle);
    }

    // Manejo genérico de RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorDetalle = new HashMap<>();
        errorDetalle.put("mensaje", ex.getMessage());
        errorDetalle.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetalle);
    }
}
