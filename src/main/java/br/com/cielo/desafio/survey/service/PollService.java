package br.com.cielo.desafio.survey.service;

import br.com.cielo.desafio.survey.resource.request.PersonRequest;
import br.com.cielo.desafio.survey.resource.request.PollRequest;
import br.com.cielo.desafio.survey.resource.response.PollResponse;
import br.com.cielo.desafio.survey.resource.response.SurveyStatistics;

import java.util.List;

public interface PollService {

    PollResponse createPoll(PollRequest request);

    List<PollResponse> getAll();

    PollResponse findByPollId(Long id);

    PollResponse findByPollTitle(String title);

    SurveyStatistics getStatistics(final Long id);

    void registerVote(PersonRequest request, Long id);
}
