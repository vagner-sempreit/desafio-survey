package br.com.cielo.desafio.survey.resource.converter;

import br.com.cielo.desafio.survey.model.Option;
import br.com.cielo.desafio.survey.model.Poll;
import br.com.cielo.desafio.survey.resource.response.OptionResponse;
import br.com.cielo.desafio.survey.resource.response.SurveyStatistics;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SurveyStatisticsConverter {

    public static SurveyStatistics fromModelToRequest(Poll poll){

        return Optional.of(poll).map(pol -> new SurveyStatistics()
                    .setTitle(poll.getTitle())
                    .setStatus(poll.getEndDate().isBefore(LocalDateTime.now()) ? "Close" : "Open")
                    .setOptions(getListOrder(poll.getOptions()))).orElse(new SurveyStatistics());
    }

    private static List<OptionResponse> getListOrder(List<Option> options) {
        return options
                .stream()
                .map(opt -> new OptionResponse().setDescription(opt.getDescription()).setAmount(opt.getAmount()))
                .sorted(Comparator.comparingLong(OptionResponse::getAmount))
                .collect(Collectors.toList());
    }
}
