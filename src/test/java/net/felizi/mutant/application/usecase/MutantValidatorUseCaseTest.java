package net.felizi.mutant.application.usecase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.application.dto.GeneticDTO;
import net.felizi.mutant.config.exception.MultipleException;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MutantValidatorUseCaseTest {
  private @Autowired MutantValidatorUseCase useCase;

  private GeneticDTO quadraticHuman;
  private GeneticDTO quadraticMutant;
  private GeneticDTO notQuadratic;

  @Before
  public void setup() {
    quadraticHuman = new GeneticDTO(new String[] { "GGATTC", "CCTAAG", "CTGTTA", "TCCTGT", "AGTAAT", "CACCTG" });
    quadraticMutant = new GeneticDTO(new String[] { "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA" });
    notQuadratic = new GeneticDTO(new String[] { "XX", "XX", "XX", "XX", "XX" });
  }

  @Test(expected = MultipleException.class)
  public void shouldThrowExceptionWhenNullParam() throws Exception {
    useCase.execute(null);
  }

  @Test(expected = MultipleException.class)
  public void shouldThrowExceptionWhenNotQuadratic() throws Exception {
    useCase.execute(notQuadratic);
  }

  @Test
  public void shouldMutantWhenMutantDNA() throws Exception {
    GeneticTypeEnum genetic = useCase.execute(quadraticMutant);
    Assert.assertEquals(GeneticTypeEnum.MUTANT, genetic);
  }

  @Test
  public void shouldHumanWhenHumanDNA() throws Exception {
    GeneticTypeEnum genetic = useCase.execute(quadraticHuman);
    Assert.assertEquals(GeneticTypeEnum.HUMAN, genetic);
  }
}