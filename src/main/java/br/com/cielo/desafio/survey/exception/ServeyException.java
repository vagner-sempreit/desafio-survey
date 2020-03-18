package br.com.cielo.desafio.survey.exception;

public class ServeyException extends RuntimeException {

    private static final long serialVersionUID = -1529491278455490000L;

    private ConstantEnum error;

    public ServeyException(String msg) {
        super(msg);
    }

    public ServeyException(ConstantEnum error){
        this.error = error;
    }

    public ServeyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ConstantEnum getError() {
        return error;
    }
}
