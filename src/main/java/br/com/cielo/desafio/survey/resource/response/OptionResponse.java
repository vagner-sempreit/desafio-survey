package br.com.cielo.desafio.survey.resource.response;

import br.com.cielo.desafio.surveycommons.resource.response.Response;

public class OptionResponse extends Response {

    private static final long serialVersionUID = -6431494140940210383L;

    private Long id;

    private String description;

    private Long amount;

    public Long getId() {
        return id;
    }

    public OptionResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OptionResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public OptionResponse setAmount(Long amount) {
        this.amount = amount;
        return this;
    }
}
