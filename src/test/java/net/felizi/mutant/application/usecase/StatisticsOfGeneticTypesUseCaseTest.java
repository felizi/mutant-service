package net.felizi.mutant.application.usecase;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.application.dto.GeneticDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StatisticsOfGeneticTypesUseCaseTest {
  private @Autowired MutantValidatorUseCase mutantValidatorUseCase;
  private @Autowired StatisticsOfGeneticTypesUseCase useCase;

  private GeneticDTO human1 = new GeneticDTO(
      new String[] { "GGATTC", "CCTAAG", "CTGTTA", "TCCTGT", "AGTAAT", "CACCTG" });
  private GeneticDTO human2 = new GeneticDTO(
      new String[] { "GGCAGG", "ATGGTC", "TAGCTG", "TCAAAG", "TAATTG", "CTGTTA" });
  private GeneticDTO human3 = new GeneticDTO(
      new String[] { "ACACCA", "GAGAGT", "TAGGTG", "GCGAAT", "TACTTG", "TGGTAA" });
  private GeneticDTO mutant1 = new GeneticDTO(
      new String[] { "GCTTCT", "GGACCT", "AGTGCG", "TGTTAC", "TGATAG", "CTAATT" });
  private GeneticDTO mutant2 = new GeneticDTO(
      new String[] { "ATGTGA", "TTGCAC", "GGTAGA", "GGAACG", "TCTTCG", "AACAAA" });

  @Before
  public void setup() throws Exception {
    mutantValidatorUseCase.execute(human1);
    mutantValidatorUseCase.execute(human2);
    mutantValidatorUseCase.execute(human3);
    mutantValidatorUseCase.execute(mutant1);
    mutantValidatorUseCase.execute(mutant2);
  }

  @Test
  public void a() throws Exception {
    Map<String, Object> result = useCase.execute();
    Assert.assertNotNull(result);
    Assert.assertNotNull(result.get("count_mutant_dna"));
    Assert.assertNotNull(result.get("count_human_dna"));
    Assert.assertNotEquals(0, result.get("count_mutant_dna"));
    Assert.assertNotEquals(0, result.get("count_human_dna"));
    Assert.assertNotNull(result.get("ratio"));
  }
}