package br.com.cielo.desafio.survey.service;

import br.com.cielo.desafio.survey.exception.ConstantEnum;
import br.com.cielo.desafio.survey.exception.ObjectNotFoundException;
import br.com.cielo.desafio.survey.exception.PollExistException;
import br.com.cielo.desafio.survey.exception.ServeyException;
import br.com.cielo.desafio.survey.model.Person;
import br.com.cielo.desafio.survey.model.Poll;
import br.com.cielo.desafio.survey.repository.PollRepository;
import br.com.cielo.desafio.survey.resource.converter.PollConverter;
import br.com.cielo.desafio.survey.resource.converter.SurveyStatisticsConverter;
import br.com.cielo.desafio.survey.resource.request.PersonRequest;
import br.com.cielo.desafio.survey.resource.request.PollRequest;
import br.com.cielo.desafio.survey.resource.response.PollResponse;
import br.com.cielo.desafio.survey.resource.response.SurveyStatistics;
import br.com.cielo.desafio.surveycommons.util.Dates;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PollServiceImpl.class);

   // @Autowired
    private PollRepository pollRepository;

    //@Autowired
    private PersonService personService;

    //@Autowired
    private OptionService optionService;
    
    //@Autowired
    public PollServiceImpl(PollRepository pollRepository, 
    						PersonService personService,
    						OptionService optionService) {
    	this.pollRepository = pollRepository;
    	this.personService = personService;
    	this.optionService = optionService;
    }

    @Override
    public PollResponse createPoll(PollRequest request) {

        LOGGER.info("m=createPoll title={}", request.getTitle());
        if (Objects.isNull(request) && StringUtils.isEmpty(request.getTitle())) {
            throw new ObjectNotFoundException("Campos Obrigatorios vazio" +
                    request.toString() + ", Tipo: " + PollRequest.class.getName());
        }
        if (pollRepository.findByTitle(request.getTitle()).isPresent()) {
            throw new PollExistException(ConstantEnum.POLL_EXISTS);
        }

        validateEndDate(Dates.getLocalDateTime(PollConverter.generateDateOk(request.getEndDate(), " 23:59:59"), "dd/MM/yyyy HH:mm:ss"));

        var poll = Optional.of(request)
                .map(PollConverter::fromRequestToModel)
                .orElseThrow(() -> new ServeyException(ConstantEnum.POLL_ERROR_CONVERTER));
        return Optional.of(poll)
                .map(entity -> pollRepository.save(entity))
                .map(PollConverter::fromModelToResponse)
                .orElseThrow(() -> new ServeyException(ConstantEnum.POLL_ERROR_SAVE));
    }

    @Override
    public List<PollResponse> getAll() {
        return null;
    }

    @Override
    public PollResponse findByPollId(Long id) {
        LOGGER.info("m=findByPollId id={}", id);
        var poll = pollRepository.findById(id).orElseThrow(() -> new ServeyException(ConstantEnum.POLL_NOT_FOUND));
        return PollConverter.fromModelToResponse(poll);
    }

    @Override
    public PollResponse findByPollTitle(String title) {
        LOGGER.info("m=findByPollTitle title={}", title);
        var poll = pollRepository.findByTitle(title).orElseThrow(() -> new ServeyException(ConstantEnum.POLL_NOT_FOUND));
        return PollConverter.fromModelToResponse(poll);
    }

    @Override
    public SurveyStatistics getStatistics(final Long id){
        LOGGER.info("m=getStatistics id={}", id);
        var poll = pollRepository.findById(id).orElseThrow(() -> new ServeyException(ConstantEnum.POLL_NOT_FOUND));
        return SurveyStatisticsConverter.fromModelToRequest(poll);
    }

    @Override
    public void registerVote(PersonRequest request, Long id){
        LOGGER.info("m=registerVote email={}", request.getEmail());

        Poll poll = pollRepository.findById(id).orElseThrow(() -> new ServeyException(ConstantEnum.POLL_NOT_FOUND));

        Person personInserted = personService.save(request, poll);
        if(Objects.nonNull(personInserted)){
            poll.getOptions()
                    .stream()
                    .filter(opt -> Objects.equals(opt.getId(), request.getOption()))
                    .findFirst().ifPresent(opt -> optionService.save(opt.getId()));
        }
    }

    private void validateEndDate(LocalDateTime endDate){
        if(endDate.isBefore(LocalDateTime.now())){
            throw new ServeyException(ConstantEnum.POLL_END_DATE_IS_AFTER);
        }
    }
}
