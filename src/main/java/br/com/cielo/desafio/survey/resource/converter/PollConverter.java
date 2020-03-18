package br.com.cielo.desafio.survey.resource.converter;

import br.com.cielo.desafio.survey.model.Poll;
import br.com.cielo.desafio.survey.model.Option;
import br.com.cielo.desafio.survey.resource.request.PollRequest;
import br.com.cielo.desafio.survey.resource.response.PollResponse;
import br.com.cielo.desafio.survey.resource.response.OptionResponse;
import br.com.cielo.desafio.surveycommons.util.Dates;
import br.com.cielo.desafio.surveycommons.util.Numbers;

import java.util.Optional;
import java.util.stream.Collectors;

public class PollConverter {

    private static String patter = "dd/MM/yyyy HH:mm:ss";

    public static Poll fromRequestToModel(PollRequest request){
        return Optional.of(request).map(req -> {
            var poll = new Poll()
                    .setEndDate(Dates.getLocalDateTime(generateDateOk(request.getEndDate(), " 23:59:59"), patter))
                    .setTitle(request.getTitle())
                    .setName(request.getName())
                    .setStartDate(Dates.getLocalDateTime(generateDateOk(request.getStartDate()," 00:00:00"), patter));

            var options = request.getOptions().stream()
                    .map(quest -> new Option().setPoll(poll).setDescription(quest.getDescription()))
                    .collect(Collectors.toList());
            return poll.setOptions(options);
        }).orElse(null);
    }

    public static String generateDateOk(String date, String complement){
        return date.length() == Numbers.TEN ? date + complement : date;
    }
    public static PollResponse fromModelToResponse(Poll model){
        return Optional.of(model).map( entity -> {
            var pollResponse = new PollResponse().setEndDate(Dates.getFormattedDate(entity.getEndDate(), patter))
                    .setStartDate(Dates.getFormattedDate(model.getStartDate(), patter))
                    .setTitle(model.getTitle());
            pollResponse.setOptions(Optional.of(model.getOptions()
                    .stream()
                    .map(opt -> new OptionResponse().setId(opt.getId()).setDescription(opt.getDescription()).setAmount(opt.getAmount())).collect(Collectors.toList())).orElse(null));
            return pollResponse;
        }).orElse(null);
    }
}
