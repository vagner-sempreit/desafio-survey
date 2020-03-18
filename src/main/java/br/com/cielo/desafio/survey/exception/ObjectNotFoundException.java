package br.com.cielo.desafio.survey.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ConstantEnum error;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(ConstantEnum error){
        this.error = error;
    }

    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ConstantEnum getError() {
        return error;
    }

    public void setError(ConstantEnum error) {
        this.error = error;
    }
}
