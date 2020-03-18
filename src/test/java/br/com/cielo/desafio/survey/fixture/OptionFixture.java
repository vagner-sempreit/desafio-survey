package br.com.cielo.desafio.survey.fixture;

import br.com.cielo.desafio.survey.model.Option;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import static br.com.six2six.fixturefactory.Fixture.of;

public class OptionFixture implements TemplateLoader {

    public static final String OPTION_OK = "OPTION_OK";
    @Override
    public void load() {
        of(Option.class).addTemplate(OPTION_OK, new Rule() {
            {
                add("description", "Flamengo");
                add(" amount", 32);
            }
        });
    }
}
