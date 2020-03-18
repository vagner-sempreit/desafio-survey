package br.com.cielo.desafio.survey.service;

import br.com.cielo.desafio.survey.exception.ConstantEnum;
import br.com.cielo.desafio.survey.exception.ServeyException;
import br.com.cielo.desafio.survey.model.Option;
import br.com.cielo.desafio.survey.repository.OptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionServiceImpl.class);

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Option save(final Long id) {
        LOGGER.info("m=save id={}", id);
        var option = optionRepository.findById(id).orElseThrow(() -> new ServeyException(ConstantEnum.OPTION_NOT_FOUND));
        return optionRepository.save(option.setAmount(option.getAmount() + 1));
    }

}
