package br.com.financeiro.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class for handling custom exceptions.
 * 
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomRestExceptionHandler {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ValidationError> objetoObrigatorio(HttpMessageNotReadableException ex) {

        ValidationError exceptionResponse = new ValidationError("Invalid MessageBody", HttpStatus.BAD_REQUEST.name(),"MessageBody");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrors> camposObrigatorios(MethodArgumentNotValidException ex) {

        ValidationErrors validationErrors = new ValidationErrors();

        BindingResult result = ex.getBindingResult();

        for(ObjectError error:result.getAllErrors()){
            validationErrors.add(error.getDefaultMessage(),"required_fields",((FieldError)error).getField());
        }

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ServiceValidationException.class)
    public ResponseEntity<ValidationErrors> excecoesNegocio(ServiceValidationException ex) {
        /*
            could return a HttpStatus.UNPROCESSABLE_ENTITY for business errors
         */
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

}
