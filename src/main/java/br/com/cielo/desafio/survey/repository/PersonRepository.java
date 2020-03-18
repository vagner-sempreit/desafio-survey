package br.com.cielo.desafio.survey.repository;

import br.com.cielo.desafio.survey.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(final String email);

    Optional<Person> findByEmailAndPollId(final String email, Long idPoll);
}
