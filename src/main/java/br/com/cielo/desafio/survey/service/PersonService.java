package br.com.cielo.desafio.survey.service;

import br.com.cielo.desafio.survey.model.Person;
import br.com.cielo.desafio.survey.model.Poll;
import br.com.cielo.desafio.survey.resource.request.PersonRequest;

public interface PersonService {

    Person save(PersonRequest request, Poll poll);
}
