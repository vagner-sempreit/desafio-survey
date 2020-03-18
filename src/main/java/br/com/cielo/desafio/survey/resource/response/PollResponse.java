package br.com.cielo.desafio.survey.resource.response;

import br.com.cielo.desafio.surveycommons.resource.response.Response;

import java.util.List;

public class PollResponse extends Response {

    private String name;

    private String title;

    private String startDate;

    private String endDate;

    private List<OptionResponse> options;

    private String owner;

    public String getName() {
        return name;
    }

    public PollResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PollResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public PollResponse setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public PollResponse setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public List<OptionResponse> getOptions() {
        return options;
    }

    public PollResponse setOptions(List<OptionResponse> options) {
        this.options = options;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public PollResponse setOwner(String owner) {
        this.owner = owner;
        return this;
    }
}
