package net.felizi.mutant.domain.tasks;

import java.util.concurrent.Callable;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;

public class DiagonalLeftTask implements Callable<GeneticTypeEnum> {
  private static final int MUTANT_REPETITIONS = 4;

  private final GeneticMatrixDTO matrix;

  public DiagonalLeftTask(GeneticMatrixDTO matrix) {
    super();
    this.matrix = matrix;
  }

  @Override
  public GeneticTypeEnum call() throws Exception {
    try {
      char[][] dna = matrix.getDna();
      for (int l = 0; l < dna.length; l++) {
        int count = 0;
        Character last = null;
        int diagonalShifter = 0;
        for (int c = 0; c < dna[l].length; c++) {
          char current = dna[l + diagonalShifter][c];
          if (last == null) {
            last = current;
          }
          if (last.charValue() == current) {
            count++;
          } else {
            count = 1;
            last = current;
          }
          if (count == MUTANT_REPETITIONS) {
            return GeneticTypeEnum.MUTANT;
          }
          diagonalShifter++;
          if (l + diagonalShifter >= dna.length) {
            break;
          }
        }
      }
    } catch (Exception e) {
      throw e;
    }
    return GeneticTypeEnum.HUMAN;
  }

}
