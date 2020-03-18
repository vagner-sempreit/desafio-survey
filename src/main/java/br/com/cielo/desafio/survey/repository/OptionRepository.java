package br.com.cielo.desafio.survey.repository;

import br.com.cielo.desafio.survey.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByPollId(final Long idPoll);
}
