package net.felizi.mutant.application.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import net.felizi.mutant.application.dto.GeneticDTO;
import net.felizi.mutant.config.exception.ErrorSpec;
import net.felizi.mutant.config.specification.Specification;

@Component
public class GeneticCorrectSizeValidator implements Specification<GeneticDTO> {

  @Override
  public Predicate<GeneticDTO> predicate() {
    return g -> {
      return g != null && g.hasDNA() && isQuadratic(g);
    };
  }

  private boolean isQuadratic(GeneticDTO g) {
    String[] dna = g.getDna();
    for (int i = 0; i < dna.length; i++) {
      if (dna[i].length() != dna.length) {
        return false;
      }
    }
    return true;
  }

  @Override
  public ErrorSpec getError() {
    return new ErrorSpec("Format or size of Genetic DNA matrix is incorrect");
  }
}