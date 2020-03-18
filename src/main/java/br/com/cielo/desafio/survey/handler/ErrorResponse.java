package br.com.cielo.desafio.survey.handler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "errorCode", "error", "errorDescription" })
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -7581493683559930808L;

    private int errorCode;

    private String error;

    private String errorDescription;

    public ErrorResponse(final String errorDescription, final HttpStatus status) {
        this.errorDescription = errorDescription;
        this.error = status.getReasonPhrase();
        this.errorCode = status.value();
    }

    public ErrorResponse(final String errorDescription, final String field, final int id) {
        this.errorDescription = errorDescription;
        this.error = field;
        this.errorCode = id;
    }

    @Override
    public String toString() {
        return new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(this);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
