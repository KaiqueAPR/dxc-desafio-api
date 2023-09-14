package com.kaiqueapr.dxcdesafioapi.configs;

import com.kaiqueapr.dxcdesafioapi.configs.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleValidationErrors(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        problemDetail.setType(e.getBody().getType());
        problemDetail.setTitle("Dado não informado corretamente");
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        String detail = String.join(", ", MethodArgumentNotValidException.errorsToStringList(errors));
        problemDetail.setDetail(detail);
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleGeneralExceptions(Exception e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        problemDetail.setTitle("Erro interno do servidor");
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(RuntimeException.class)
    ProblemDetail handleRuntimeExceptions(RuntimeException e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        problemDetail.setTitle("Erro interno do servidor");
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(UserNotFound.class)
    ProblemDetail handleUsuarioNotFoundException(UserNotFound e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        problemDetail.setTitle("Usuário não encontrado");
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

}
