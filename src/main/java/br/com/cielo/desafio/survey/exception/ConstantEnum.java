package br.com.cielo.desafio.survey.exception;

import org.springframework.http.HttpStatus;

public enum ConstantEnum {
    POLL_EXISTS("001", HttpStatus.BAD_REQUEST, "Pesquisa ja cadastrada"),
    POLL_NOT_FOUND("002", HttpStatus.NOT_FOUND, "Pesquisa nao encontrada"),
    POLL_ERROR_CONVERTER("003", HttpStatus.CONFLICT, "Erro na Converter"),
    POLL_ERROR_SAVE("004", HttpStatus.CONFLICT, "Error ao gravar no Banco"),
    POLL_END_DATE_IS_AFTER("005", HttpStatus.BAD_REQUEST, "Enquete com data finalaizada"),
    INVALID_FIELD_TO_VALIDATE(" 006", HttpStatus.BAD_REQUEST, "invalid.validate.field"),
    INVALID_NULL_TO_VALIDATE(" 007", HttpStatus.BAD_REQUEST, "invalid.null.field"),
    OPTION_NOT_FOUND("008", HttpStatus.NOT_FOUND, "Opcão não encontrada"),
    PERSON_EXISTS("009", HttpStatus.BAD_REQUEST, "Pessoa ja votou nessa pesquisa"),
    PERSON_EMAIL_EMPTY("010", HttpStatus.BAD_REQUEST, "E-mail em Branco");

    ConstantEnum(String code, HttpStatus httpStatus,  String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    private  String code;
    private  String description;
    private  HttpStatus httpStatus;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
