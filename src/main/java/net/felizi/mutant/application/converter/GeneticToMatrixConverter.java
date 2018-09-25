package net.felizi.mutant.application.converter;

import org.springframework.stereotype.Component;

import net.felizi.mutant.application.dto.GeneticDTO;
import net.felizi.mutant.application.dto.GeneticMatrixDTO;

@Component
public class GeneticToMatrixConverter {

  public GeneticMatrixDTO execute(GeneticDTO genetic) {
    char[][] matrix = null;
    if (isValid(genetic)) {
      String[] dna = genetic.getDna();
      int size = dna.length;
      matrix = new char[size][size];
      for (int x = 0; x < size; x++) {
        String line = dna[x];
        for (int y = 0; y < size; y++) {
          matrix[x][y] = line.charAt(y);
        }
      }
    }
    return new GeneticMatrixDTO(matrix);
  }

  private boolean isValid(GeneticDTO genetic) {
    return genetic != null && genetic.hasDNA();
  }
}
