package net.felizi.mutant.application.usecase;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.felizi.mutant.application.translator.StatisticsOfGeneticTypesTranslator;
import net.felizi.mutant.domain.service.StatisticsOfGeneticTypesService;

@Component
public class StatisticsOfGeneticTypesUseCase {
  private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsOfGeneticTypesUseCase.class);

  private @Autowired StatisticsOfGeneticTypesService service;
  private @Autowired StatisticsOfGeneticTypesTranslator translator;

  public Map<String, Object> execute() throws Exception {
    LOGGER.debug("Retrieve statistics of genetic types");
    try {
      return translator.translate(service.execute());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw e;
    }
  }
}