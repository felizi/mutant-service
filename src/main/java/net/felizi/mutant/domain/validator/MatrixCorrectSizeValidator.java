package net.felizi.mutant.domain.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.config.exception.ErrorSpec;
import net.felizi.mutant.config.specification.Specification;

@Component
public class MatrixCorrectSizeValidator implements Specification<GeneticMatrixDTO> {
  private static final int LINES_SIZE = 6;
  private static final int COLUMNS_SIZE = 6;

  @Override
  public Predicate<GeneticMatrixDTO> predicate() {
    return m -> {
      char[][] dna = m.getDna();
      int lines = dna.length;
      if (lines == LINES_SIZE) {
        for (int i = 0; i < lines; i++) {
          int columns = dna[i].length;
          if (columns != COLUMNS_SIZE) {
            return false;
          }
        }
        return true;
      }
      return false;
    };
  }

  @Override
  public ErrorSpec getError() {
    return new ErrorSpec("Matrix size is incorrect");
  }

}
