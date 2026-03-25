package br.com.centroinfo.api.exceptions;

import br.com.centroinfo.api.dtos.ErrorDTO.ErrorResponseDTO;
import br.com.centroinfo.api.providers.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> treatResourceNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(Exception.class) /*Tratamento genérico */
    public ResponseEntity<ErrorResponseDTO> treatErrorGeneral(
            Exception ex, HttpServletRequest request) {
        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
