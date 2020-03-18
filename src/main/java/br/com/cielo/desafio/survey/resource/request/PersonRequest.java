package br.com.cielo.desafio.survey.resource.request;

import br.com.cielo.desafio.surveycommons.resource.request.Request;

public class PersonRequest extends Request {

    private String email;

    private String name;

    private Long option;

    public String getEmail() {
        return email;
    }

    public PersonRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public PersonRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOption() {
        return option;
    }

    public PersonRequest setOption(Long option) {
        this.option = option;
        return this;
    }
}
