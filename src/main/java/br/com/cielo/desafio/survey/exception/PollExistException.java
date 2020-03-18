package br.com.cielo.desafio.survey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PollExistException extends RuntimeException {

    private static final long serialVersionUID = -1852373584263914046L;

    private ConstantEnum error;

    public PollExistException(String msg) {
        super(msg);
    }

    public PollExistException(ConstantEnum error){
        this.error = error;
    }
    public PollExistException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ConstantEnum getError() {
        return error;
    }

    public void setError(ConstantEnum error) {
        this.error = error;
    }
}
