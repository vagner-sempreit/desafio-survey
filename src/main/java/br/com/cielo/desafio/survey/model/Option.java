package br.com.cielo.desafio.survey.model;

import br.com.cielo.desafio.surveycommons.model.DomainModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = " question")
public class Option extends DomainModel {

    private String description;

    @Column(name = "amount", columnDefinition = "NUMBER(2) default 0", insertable = false, updatable = true)
    private Long amount;

    @ManyToOne
    private Poll poll;

    public String getDescription() {
        return description;
    }

    public Option setDescription(String description) {
        this.description = description;
        return this;
    }

    public Poll getPoll() {
        return poll;
    }

    public Option setPoll(Poll poll) {
        this.poll = poll;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public Option setAmount(Long amount) {
        this.amount = amount;
        return this;
    }
}
