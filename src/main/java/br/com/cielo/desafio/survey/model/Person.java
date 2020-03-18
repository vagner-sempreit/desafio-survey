package br.com.cielo.desafio.survey.model;

import br.com.cielo.desafio.surveycommons.model.DomainModel;

import javax.persistence.*;


@Entity
@Table(name = " person")
public class Person extends DomainModel {

    private static final long serialVersionUID = -8630405799354598960L;

    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    //@MapsId
    private Poll poll;

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public Poll getPoll() {
        return poll;
    }

    public Person setPoll(Poll poll) {
        this.poll = poll;
        return this;
    }
}
