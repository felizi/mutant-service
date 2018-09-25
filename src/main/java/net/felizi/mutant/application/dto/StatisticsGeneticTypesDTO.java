package net.felizi.mutant.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsGeneticTypesDTO extends AbstractDTO {
  private static final long serialVersionUID = 1L;

  @JsonProperty("count_mutant_dna")
  private final long countMutantDna;
  @JsonProperty("count_human_dna")
  private final long countHumanDna;
  @JsonProperty("ratio")
  private final float ratio;

  public StatisticsGeneticTypesDTO(long countMutantDna, long countHumanDna, float ratio) {
    super();
    this.countMutantDna = countMutantDna;
    this.countHumanDna = countHumanDna;
    this.ratio = ratio;
  }

  public long getCountMutantDna() {
    return countMutantDna;
  }

  public long getCountHumanDna() {
    return countHumanDna;
  }

  public float getRatio() {
    return ratio;
  }

  @Override
  public String toString() {
    return "StatisticsGeneticTypesDTO [countMutantDna=" + countMutantDna + ", countHumanDna=" + countHumanDna
        + ", ratio=" + ratio + "]";
  }

}