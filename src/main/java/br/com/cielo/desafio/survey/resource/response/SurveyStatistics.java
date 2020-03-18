package br.com.cielo.desafio.survey.resource.response;

import java.io.Serializable;
import java.util.List;

public class SurveyStatistics implements Serializable {


    private static final long serialVersionUID = -8067821202703557447L;

    private String title;

    private String Status;

    private List<OptionResponse> options;

    public String getTitle() {
        return title;
    }

    public SurveyStatistics setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStatus() {
        return Status;
    }

    public SurveyStatistics setStatus(String status) {
        Status = status;
        return this;
    }

    public List<OptionResponse> getOptions() {
        return options;
    }

    public SurveyStatistics setOptions(List<OptionResponse> options) {
        this.options = options;
        return this;
    }
}
