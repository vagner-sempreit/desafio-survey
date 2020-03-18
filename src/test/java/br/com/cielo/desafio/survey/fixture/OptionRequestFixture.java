package br.com.cielo.desafio.survey.fixture;

import br.com.cielo.desafio.survey.resource.request.OptionRequest;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import static br.com.six2six.fixturefactory.Fixture.of;

public class OptionRequestFixture implements TemplateLoader {

    public static final String OPTION_REQUEST_OK = "OPTION_REQUEST_OK";

    @Override
    public void load() {
        of(OptionRequest.class).addTemplate(OPTION_REQUEST_OK, new Rule() {
            {
                add(" description", "Bruno Covas");
            }
        });
    }
}
