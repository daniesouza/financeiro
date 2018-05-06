package br.com.financeiro.exception.handler;

import javax.validation.ValidationException;

public class ServiceValidationException extends ValidationException {

    private static final long serialVersionUID = 2989052147284226837L;

    private final ValidationErrors validationErrors = new ValidationErrors();

    public ServiceValidationException(Throwable cause){
        super(cause);
    }

    public ServiceValidationException() {
        super("ServiceValidationException - Error");
    }

    public ServiceValidationException(String message, String code, String field) {
        this.validationErrors.add(message,code,field);
    }

    public ServiceValidationException(ValidationError validationError) {
        this.validationErrors.add(validationError);
    }

    public ServiceValidationException(Exception ex){
        super(ex);
    }

    @Override
    public String getMessage() {
        if(!hasError()) {
            return super.getMessage();
        } else {
            return getMensages();
        }
    }


    public void addError(String message,String code,String field) {
        this.validationErrors.add(message,code,field);
    }

    public void addError(ValidationError validationError) {
        this.validationErrors.add(validationError);
    }


    public ValidationErrors getErrors() {
        return validationErrors;
    }

    public boolean hasError() {
        return !validationErrors.getErrors().isEmpty();
    }

    private String getMensages() {
        StringBuilder sb = new StringBuilder();
        for (ValidationError msg : validationErrors.getErrors()) {
            sb.append(msg.toString());
            sb.append(";");
        }
        return sb.substring(0, sb.length()-1);
    }

}