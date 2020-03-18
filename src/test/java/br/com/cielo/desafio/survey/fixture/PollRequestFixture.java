package br.com.cielo.desafio.survey.fixture;

import br.com.cielo.desafio.survey.resource.request.OptionRequest;
import br.com.cielo.desafio.survey.resource.request.PollRequest;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.List;

import static br.com.six2six.fixturefactory.Fixture.of;

public class PollRequestFixture implements TemplateLoader {
    public static final String POLL_REQUEST_OK = "POLL_REQUEST_OK";

    private String name;

    private String title;

    private List<OptionRequest> options;

    private String startDate;

    private String endDate;


    @Override
    public void load() {
        of(PollRequest.class).addTemplate(POLL_REQUEST_OK, new Rule() {
            {
                add(" name", "teste");
                add(" title", "eleicao prefeito");
                add(" options",  has(3).of(OptionRequest.class, OptionRequestFixture.OPTION_REQUEST_OK));
                add(" startDate", "17/03/2020 00:00:00");
                add(" endDate", "19/03/2020 00:00:00");
            }
        });
    }

}
