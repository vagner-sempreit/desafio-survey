package br.com.cielo.desafio.survey.fixture;

import br.com.cielo.desafio.survey.model.Option;
import br.com.cielo.desafio.survey.model.Poll;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import static br.com.six2six.fixturefactory.Fixture.of;

public class PollFixture implements TemplateLoader {

    public static final String POLL_OK = "POLL_OK";

    @Override
    public void load() {
        of(Poll.class).addTemplate(POLL_OK, new Rule() {
            {
                add("id", 1);
                add("name", "TESTE1");
                add("title", "eleicao prefeito");
                add("options", has(1).of(Option.class, OptionFixture.OPTION_OK));
            }
        });
    }
}
