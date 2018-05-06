package br.com.financeiro.exception.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrors implements Serializable {


    private static final long serialVersionUID = -8018577714458403653L;

    private List<ValidationError> errors = new ArrayList<>();

    public void add(String message,String code,String field){
        errors.add(new ValidationError(message,code, field));
    }

    public void add(ValidationError validationError){
        errors.add(validationError);
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

}
