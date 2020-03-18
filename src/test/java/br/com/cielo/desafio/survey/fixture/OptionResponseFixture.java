package br.com.cielo.desafio.survey.fixture;

import br.com.cielo.desafio.survey.resource.response.OptionResponse;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import static br.com.six2six.fixturefactory.Fixture.of;

public class OptionResponseFixture implements TemplateLoader {

    public static final String OPTION_RESPONSE_OK = "OPTION_RESPONSE_OK";

    @Override
    public void load() {
        of(OptionResponse.class).addTemplate(OPTION_RESPONSE_OK, new Rule() {
            {
                add("id", 1);
                add(" description", "Bruno Covas");
                add("amount", 2);
            }
        });


    }
}
