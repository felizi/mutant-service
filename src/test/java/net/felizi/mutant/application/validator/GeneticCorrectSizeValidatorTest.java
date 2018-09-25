package net.felizi.mutant.application.validator;

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
public class GeneticCorrectSizeValidatorTest {
  private @Autowired GeneticCorrectSizeValidator validator;

  private GeneticDTO quadraticHuman;
  private GeneticDTO quadraticMutant;
  private GeneticDTO notQuadratic;
  private GeneticDTO empty;
  private GeneticDTO incorrectSize;
  private GeneticDTO correctSize;

  @Before
  public void setup() {
    quadraticHuman = new GeneticDTO(new String[] { "GGATTC", "CCTAAG", "CTGTTA", "TCCTGT", "AGTAAT", "CACCTG" });
    quadraticMutant = new GeneticDTO(new String[] { "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA" });
    notQuadratic = new GeneticDTO(new String[] { "XX", "XX", "XX", "XX", "XX" });
    empty = new GeneticDTO(new String[] {});
    incorrectSize = new GeneticDTO(new String[] { "AA", "AAA", "AAAA", "AAAAA", "AAAAAA" });
    correctSize = new GeneticDTO(new String[] { "WWWWW", "WWWWW", "WWWWW", "WWWWW", "WWWWW" });
  }

  @Test
  public void shouldCorrectSizeWhenQuadriaticHuman() throws Exception {
    Assert.assertTrue(validator.predicate().test(quadraticHuman));
  }

  @Test
  public void shouldCorrectSizeWhenQuadriaticMutant() throws Exception {
    Assert.assertTrue(validator.predicate().test(quadraticMutant));
  }

  @Test
  public void shouldIncorrectSizeWhenNotQuadriatic() throws Exception {
    Assert.assertFalse(validator.predicate().test(notQuadratic));
  }

  @Test
  public void shouldIncorrectSizeWhenEmpty() throws Exception {
    Assert.assertFalse(validator.predicate().test(empty));
  }

  @Test
  public void shouldIncorrectSizeWhenIncorrectSize() throws Exception {
    Assert.assertFalse(validator.predicate().test(incorrectSize));
  }

  @Test
  public void shouldCorrectSizeWhenCorrectSize() throws Exception {
    Assert.assertTrue(validator.predicate().test(correctSize));
  }
}