package br.com.cielo.desafio.survey.repository;

import br.com.cielo.desafio.survey.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    Optional<Poll> findById(Long id);

    Optional<Poll> findByTitle(final String title);

}
