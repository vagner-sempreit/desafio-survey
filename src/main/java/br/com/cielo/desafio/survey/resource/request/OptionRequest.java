package br.com.cielo.desafio.survey.resource.request;

import br.com.cielo.desafio.surveycommons.resource.request.Request;

public class OptionRequest extends Request {

    private String description;

    public String getDescription() {
        return description;
    }

    public OptionRequest setDescription(String description) {
        this.description = description;
        return this;
    }
}
