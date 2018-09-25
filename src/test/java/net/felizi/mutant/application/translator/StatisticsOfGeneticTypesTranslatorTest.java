package net.felizi.mutant.application.translator;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.application.dto.StatisticsGeneticTypesDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StatisticsOfGeneticTypesTranslatorTest {

  private @Autowired StatisticsOfGeneticTypesTranslator translator;

  @Test
  public void shouldReturnNullWhenNullParameter() {
    Map<String, Object> result = translator.translate(null);
    Assert.assertNull(result);
  }

  @Test
  public void shouldReturnEmptyWhenEmptyParameter() {
    Map<String, Object> result = translator.translate(new StatisticsGeneticTypesDTO(0, 0, 0));
    Assert.assertNotNull(result);
  }

  @Test
  public void shouldReturnValidWhenNotNullParameter() {
    long countMutantDna = 4494;
    long countHumanDna = 546154;
    float ratio = 42.25f;
    Map<String, Object> result = translator
        .translate(new StatisticsGeneticTypesDTO(countMutantDna, countHumanDna, ratio));
    Assert.assertNotNull(result);
    Assert.assertEquals(countMutantDna, result.get("count_mutant_dna"));
    Assert.assertEquals(countHumanDna, result.get("count_human_dna"));
    Assert.assertEquals(ratio, result.get("ratio"));
  }
}