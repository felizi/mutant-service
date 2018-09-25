package net.felizi.mutant.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.config.specification.Valitador;
import net.felizi.mutant.domain.entity.GeneticEntity;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;
import net.felizi.mutant.domain.repository.GeneticRepository;
import net.felizi.mutant.domain.validator.MatrixDNACorrectTypesValidator;
import net.felizi.mutant.domain.validator.MatrixIsNotEmptyValidator;

@Service
public class IdentifyTypeOfDNAService {
  private static final Logger LOGGER = LoggerFactory.getLogger(IdentifyTypeOfDNAService.class);
  private @Autowired GeneticRepository repository;
  private final Valitador<GeneticMatrixDTO> pattern;
  private @Autowired IdentifyTypeOfDNAParallelExecutorService parallel;

  @SuppressWarnings("unchecked")
  public @Autowired IdentifyTypeOfDNAService(MatrixIsNotEmptyValidator matrixIsNotEmptyValidator,
      MatrixDNACorrectTypesValidator matrixDNACorrectTypesValidator) {
    super();
    this.pattern = Valitador.of(matrixIsNotEmptyValidator, matrixDNACorrectTypesValidator);
  }

  @Transactional(rollbackFor = Throwable.class)
  public GeneticTypeEnum execute(GeneticMatrixDTO matrix) throws Exception {
    LOGGER.debug("Verify DNA from matrix={}", matrix);
    try {
      pattern.validade(matrix);
      GeneticTypeEnum geneticTypeOfDNA = parallel.execute(matrix);
      persist(geneticTypeOfDNA);
      return geneticTypeOfDNA;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw e;
    }
  }

  private void persist(GeneticTypeEnum geneticTypeOfDNA) {
    GeneticEntity geneticEntity = new GeneticEntity();
    geneticEntity.setType(geneticTypeOfDNA);
    repository.save(geneticEntity);
  }
}