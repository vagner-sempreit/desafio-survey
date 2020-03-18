package br.com.cielo.desafio.survey.resource.request;

import br.com.cielo.desafio.surveycommons.resource.request.Request;

import java.util.List;

public class PollRequest extends Request {

    private String name;

    private String title;

    private List<OptionRequest> options;

    private String startDate;

    private String endDate;

    public String getName() {
        return name;
    }

    public PollRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PollRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<OptionRequest> getOptions() {
        return options;
    }

    public PollRequest setOptions(List<OptionRequest> options) {
        this.options = options;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public PollRequest setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public PollRequest setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }
}
