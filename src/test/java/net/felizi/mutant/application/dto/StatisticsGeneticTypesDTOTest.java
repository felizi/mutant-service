package net.felizi.mutant.application.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StatisticsGeneticTypesDTOTest {

  @Test
  public void shouldReturnZerosWhenZerosParameter() {
    long countMutantDna = 0;
    long countHumanDna = 0;
    float ratio = 0;
    StatisticsGeneticTypesDTO sgt = new StatisticsGeneticTypesDTO(countMutantDna, countHumanDna, ratio);
    Assert.assertNotNull(sgt);
    Assert.assertEquals(sgt.getCountHumanDna(), countHumanDna);
    Assert.assertEquals(sgt.getCountMutantDna(), countMutantDna);
    Assert.assertEquals(sgt.getRatio(), ratio, 0);
  }

  @Test
  public void shouldReturnNegativeWhenNegativeParameter() {
    long countMutantDna = -4250;
    long countHumanDna = -30;
    float ratio = -25.2f;
    StatisticsGeneticTypesDTO sgt = new StatisticsGeneticTypesDTO(countMutantDna, countHumanDna, ratio);
    Assert.assertNotNull(sgt);
    Assert.assertEquals(sgt.getCountHumanDna(), countHumanDna);
    Assert.assertEquals(sgt.getCountMutantDna(), countMutantDna);
    Assert.assertEquals(sgt.getRatio(), ratio, 0);
  }

  @Test
  public void shouldReturnPositiveWhenPositiveParameter() {
    long countMutantDna = 4494;
    long countHumanDna = 546154;
    float ratio = 42.25f;
    StatisticsGeneticTypesDTO sgt = new StatisticsGeneticTypesDTO(countMutantDna, countHumanDna, ratio);
    Assert.assertNotNull(sgt);
    Assert.assertEquals(sgt.getCountHumanDna(), countHumanDna);
    Assert.assertEquals(sgt.getCountMutantDna(), countMutantDna);
    Assert.assertEquals(sgt.getRatio(), ratio, 0);
  }
}
