package net.felizi.mutant.domain.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.config.exception.ErrorSpec;
import net.felizi.mutant.config.specification.Specification;

@Component
public class MatrixIsNotEmptyValidator implements Specification<GeneticMatrixDTO> {

  @Override
  public Predicate<GeneticMatrixDTO> predicate() {
    return m -> m != null && m.hasDNA();
  }

  @Override
  public ErrorSpec getError() {
    return new ErrorSpec("Matrix could not be empty");
  }

}
