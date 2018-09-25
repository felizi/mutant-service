package net.felizi.mutant.application.dto;

import java.util.Arrays;

public class GeneticMatrixDTO extends AbstractDTO {
  private static final long serialVersionUID = 1L;

  private final char[][] dna;

  public GeneticMatrixDTO(char[][] dna) {
    super();
    this.dna = dna;
  }

  @Override
  public String toString() {
    return "GeneticMatrixDTO [dna=" + Arrays.toString(dna) + "]";
  }

  public boolean hasDNA() {
    return dna != null && dna.length > 0;
  }

  public char[][] getDna() {
    return dna;
  }
}
