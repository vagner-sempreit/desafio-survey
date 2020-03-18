package br.com.cielo.desafio.survey.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = " poll")
public class Poll implements Serializable {

    private static final long serialVersionUID = 2093459624380129038L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private List<Option> options;

    @Column(name="startDate")
    private LocalDateTime startDate;

    @Column(name="endDate")
    private LocalDateTime endDate;

    public Long getId() {
        return id;
    }

    public Poll setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Poll setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Poll setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Option> getOptions() {
        return options;
    }

    public Poll setOptions(List<Option> options) {
        this.options = options;
        return this;
    }

    public void addOption(Option option) {
        if (options == null) {
            options = new ArrayList<>();
        }
        option.setPoll(this);
        options.add(option);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Poll setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Poll setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }


    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", options=" + options +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, true);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }


}
