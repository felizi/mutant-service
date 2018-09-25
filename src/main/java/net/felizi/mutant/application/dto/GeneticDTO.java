package net.felizi.mutant.application.dto;

import java.util.Arrays;

public class GeneticDTO extends AbstractDTO {
  private static final long serialVersionUID = 1L;
  private String[] dna;

  public GeneticDTO() {
    super();
  }

  public GeneticDTO(String[] dna) {
    super();
    this.dna = dna;
  }

  public String[] getDna() {
    return dna;
  }

  public void setDna(String[] dna) {
    this.dna = dna;
  }

  @Override
  public String toString() {
    return "GeneticDTO [dna=" + Arrays.toString(dna) + "]";
  }

  public boolean hasDNA() {
    return dna != null && dna.length > 0;
  }

}