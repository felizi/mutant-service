package net.felizi.mutant.application.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.felizi.mutant.application.converter.GeneticToMatrixConverter;
import net.felizi.mutant.application.dto.GeneticDTO;
import net.felizi.mutant.application.validator.GeneticCorrectSizeValidator;
import net.felizi.mutant.config.specification.Valitador;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;
import net.felizi.mutant.domain.service.IdentifyTypeOfDNAService;

@Component
public class MutantValidatorUseCase {
  private static final Logger LOGGER = LoggerFactory.getLogger(MutantValidatorUseCase.class);

  private @Autowired IdentifyTypeOfDNAService checkDNAService;
  private @Autowired GeneticToMatrixConverter converter;

  private final Valitador<GeneticDTO> pattern;

  @SuppressWarnings("unchecked")
  public @Autowired MutantValidatorUseCase(GeneticCorrectSizeValidator geneticCorrectSizeValidator) {
    super();
    this.pattern = Valitador.of(geneticCorrectSizeValidator);
  }

  public GeneticTypeEnum execute(GeneticDTO genetic) throws Exception {
    LOGGER.debug("Execute mutant validador with genetic={}", genetic);
    try {
      pattern.validade(genetic);
      return checkDNAService.execute(converter.execute(genetic));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw e;
    }
  }
}