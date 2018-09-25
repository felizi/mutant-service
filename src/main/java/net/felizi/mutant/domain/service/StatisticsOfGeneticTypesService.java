package net.felizi.mutant.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.felizi.mutant.application.dto.StatisticsGeneticTypesDTO;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;
import net.felizi.mutant.domain.repository.GeneticRepository;

@Service
public class StatisticsOfGeneticTypesService {
  private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsOfGeneticTypesService.class);
  private @Autowired GeneticRepository repository;

  @Transactional(rollbackFor = Throwable.class)
  public StatisticsGeneticTypesDTO execute() throws Exception {
    LOGGER.debug("Retrieve statistics of genetic types");
    try {
      long mutants = repository.countByType(GeneticTypeEnum.MUTANT);
      long humans = repository.countByType(GeneticTypeEnum.HUMAN);
      return new StatisticsGeneticTypesDTO(mutants, humans, ratio(mutants, humans));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw e;
    }
  }

  private float ratio(long mutants, long humans) {
    mutants = Math.max(mutants, 1);
    humans = Math.max(humans, 1);
    return (float) mutants / (float) humans;
  }
}