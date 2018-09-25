package net.felizi.mutant.domain.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StatisticsOfGeneticTypesServiceTest {
  private @Autowired IdentifyTypeOfDNAService dnaService;
  private @Autowired StatisticsOfGeneticTypesService service;

  private GeneticMatrixDTO human = null;
  private GeneticMatrixDTO mutant = null;

  @Before
  public void setup() {

    human = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'A', 'G', 'A', 'T', 'A', 'A' }, //
        new char[] { 'C', 'A', 'A', 'G', 'C', 'A' }, //
        new char[] { 'T', 'C', 'G', 'T', 'A', 'C' }, //
        new char[] { 'A', 'A', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'T', 'C', 'G', 'A' }, //
        new char[] { 'A', 'A', 'T', 'A', 'A', 'A' }, });

    mutant = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'A', 'G', 'A', 'T', 'A', 'A' }, //
        new char[] { 'A', 'A', 'A', 'G', 'A', 'A' }, //
        new char[] { 'A', 'C', 'G', 'T', 'A', 'C' }, //
        new char[] { 'A', 'A', 'C', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'T', 'C', 'G', 'A' }, //
        new char[] { 'A', 'A', 'T', 'A', 'A', 'A' }, });

  }

  @Test
  public void shouldIncrementHumanWhenHuman() throws Exception {
    long mutants = service.execute().getCountMutantDna();
    long humans = service.execute().getCountHumanDna();
    Assert.assertEquals(GeneticTypeEnum.HUMAN, dnaService.execute(human));
    Assert.assertEquals(mutants, service.execute().getCountMutantDna());
    Assert.assertEquals(humans + 1, service.execute().getCountHumanDna());
  }

  @Test
  public void shouldIncrementMutantWhenMutant() throws Exception {
    long mutants = service.execute().getCountMutantDna();
    long humans = service.execute().getCountHumanDna();
    Assert.assertEquals(GeneticTypeEnum.MUTANT, dnaService.execute(mutant));
    Assert.assertEquals(mutants + 1, service.execute().getCountMutantDna());
    Assert.assertEquals(humans, service.execute().getCountHumanDna());
  }

  @Test
  public void shouldNotIncrementWhenNull() throws Exception {
    long mutants = service.execute().getCountMutantDna();
    long humans = service.execute().getCountHumanDna();
    try {
      dnaService.execute(null);
    } catch (Exception e) {
    }
    Assert.assertEquals(mutants, service.execute().getCountMutantDna());
    Assert.assertEquals(humans, service.execute().getCountHumanDna());
  }

  @Test
  public void shouldNotIncrementWhenEmpty() throws Exception {
    long mutants = service.execute().getCountMutantDna();
    long humans = service.execute().getCountHumanDna();
    try {
      dnaService.execute(new GeneticMatrixDTO(null));
    } catch (Exception e) {
    }
    Assert.assertEquals(mutants, service.execute().getCountMutantDna());
    Assert.assertEquals(humans, service.execute().getCountHumanDna());
  }

}