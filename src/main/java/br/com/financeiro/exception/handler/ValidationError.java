package br.com.financeiro.exception.handler;

import java.io.Serializable;

public class ValidationError implements Serializable {


    private static final long serialVersionUID = -3185341134314471607L;
    private String field;
    private String message;
    private String code;

    public ValidationError(String message,String code, String field){
        this.message = message;
        this.code = code;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String interCode) {
        this.code = interCode;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}