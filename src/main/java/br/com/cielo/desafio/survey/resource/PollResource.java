package br.com.cielo.desafio.survey.resource;

import br.com.cielo.desafio.survey.resource.request.PersonRequest;
import br.com.cielo.desafio.survey.resource.request.PollRequest;
import br.com.cielo.desafio.survey.resource.response.PollResponse;
import br.com.cielo.desafio.survey.resource.response.SurveyStatistics;
import br.com.cielo.desafio.survey.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/poll", produces = MediaType.APPLICATION_JSON_VALUE)
public class PollResource {

    @Autowired
    private PollService pollService;

    @PostMapping
    public PollResponse createPoll(@RequestBody PollRequest request){
        return pollService.createPoll(request);
    }

    @GetMapping("/{id}")
    public PollResponse getPools(@PathVariable("id") Long id){
        return pollService.findByPollId(id);
    }

    @GetMapping("/{id}/stats")
    public SurveyStatistics getStatistics(@PathVariable("id") Long id){
        return pollService.getStatistics(id);
    }

    @PostMapping("/{id}/vote")
    public void registerVote(@PathVariable("id") Long id,
                             @RequestBody PersonRequest request){
        pollService.registerVote(request, id);
    }


}
