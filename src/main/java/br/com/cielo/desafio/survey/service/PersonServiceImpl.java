package br.com.cielo.desafio.survey.service;

import br.com.cielo.desafio.survey.exception.ConstantEnum;
import br.com.cielo.desafio.survey.exception.ServeyException;
import br.com.cielo.desafio.survey.model.Person;
import br.com.cielo.desafio.survey.model.Poll;
import br.com.cielo.desafio.survey.repository.PersonRepository;
import br.com.cielo.desafio.survey.resource.request.PersonRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    private void validatePerson(PersonRequest request, Long id){
        if(StringUtils.isEmpty(request.getEmail())){
            throw new ServeyException(ConstantEnum.PERSON_EMAIL_EMPTY);
        }

        if(personRepository.findByEmailAndPollId(request.getEmail(), id).isPresent()){
            throw new ServeyException(ConstantEnum.PERSON_EXISTS);
        }
    }

    @Override
    public Person save(PersonRequest request, Poll poll) {
        validatePerson(request, poll.getId());
        return personRepository.save(new Person().setEmail(request.getEmail()).setName(request.getName()).setPoll(poll));
    }

}
