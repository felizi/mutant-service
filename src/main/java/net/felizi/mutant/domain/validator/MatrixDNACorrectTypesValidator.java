package net.felizi.mutant.domain.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.config.exception.ErrorSpec;
import net.felizi.mutant.config.specification.Specification;
import net.felizi.mutant.domain.enums.NucleotideEnum;

@Component
public class MatrixDNACorrectTypesValidator implements Specification<GeneticMatrixDTO> {

  @Override
  public Predicate<GeneticMatrixDTO> predicate() {
    return m -> {
      if (m == null || !m.hasDNA()) {
        return false;
      }
      char[][] dna = m.getDna();
      for (int l = 0; l < dna.length; l++) {
        for (int c = 0; c < dna[l].length; c++) {
          char nucleotide = dna[l][c];
          NucleotideEnum[] nucleotides = NucleotideEnum.values();
          boolean found = false;
          for (int v = 0; v < nucleotides.length; v++) {
            if (nucleotides[v].getSymbol() == nucleotide) {
              found = true;
              break;
            }
          }
          if (!found) {
            return found;
          }
        }
      }
      return true;
    };
  }

  @Override
  public ErrorSpec getError() {
    return new ErrorSpec("Matrix of nucleotide has incorrect values");
  }

}
