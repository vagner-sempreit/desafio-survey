package br.com.cielo.desafio.survey.fixture;

import br.com.cielo.desafio.survey.resource.response.OptionResponse;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDateTime;

import static br.com.six2six.fixturefactory.Fixture.of;

public class PollResponseFixture implements TemplateLoader {

    public static final String POLL_RESPONSE_OK = "POLL_RESPONSE_OK";

    @Override
    public void load() {
        of(OptionResponse.class).addTemplate(POLL_RESPONSE_OK, new Rule() {
            {
                add("name", "teste");
                add(" title", "eleicao prefeito");
                add("startDate", LocalDateTime.now());
                add("endDate", LocalDateTime.now().plusDays(10));
                add("options", has(1).of(OptionResponse.class, OptionResponseFixture.OPTION_RESPONSE_OK));
            }
        });


    }
}
