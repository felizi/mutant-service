package net.felizi.mutant.domain.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MatrixDNACorrectTypesValidatorTest {
  private @Autowired MatrixDNACorrectTypesValidator validator;

  private GeneticMatrixDTO correctDNA;
  private GeneticMatrixDTO incorrectDNA;
  private GeneticMatrixDTO empty;
  private GeneticMatrixDTO specialCharDNA;

  @Before
  public void setup() {
    correctDNA = new GeneticMatrixDTO(new char[][] { charArray("GGATTC"), charArray("CCTAAG"), charArray("CTGTTA"),
        charArray("TCCTGT"), charArray("AGTAAT"), charArray("CACCTG") });
    incorrectDNA = new GeneticMatrixDTO(
        new char[][] { charArray("XX"), charArray("XX"), charArray("XX"), charArray("XX"), charArray("XX") });
    empty = new GeneticMatrixDTO(new char[][] {});
    specialCharDNA = new GeneticMatrixDTO(new char[][] { charArray("Q@$A%W%"), charArray("@%Q@%Q@%"),
        charArray("(&!*@%(*@#%"), charArray("!(@#)*)#!@"), charArray("(!)@*!@#*") });
  }

  private char[] charArray(String vlr) {
    return vlr.toCharArray();
  }

  @Test
  public void shouldTrueWhenCorrectDNA() throws Exception {
    Assert.assertTrue(validator.predicate().test(correctDNA));
  }

  @Test
  public void shouldFalseWhenIcorrectDNA() throws Exception {
    Assert.assertFalse(validator.predicate().test(incorrectDNA));
  }

  @Test
  public void shouldFalseWhenEmptyDNA() throws Exception {
    Assert.assertFalse(validator.predicate().test(empty));
  }

  @Test
  public void shouldFalseWhenNullDNA() throws Exception {
    Assert.assertFalse(validator.predicate().test(null));
  }

  @Test
  public void shouldFalseWhenSpecialDNA() throws Exception {
    Assert.assertFalse(validator.predicate().test(specialCharDNA));
  }
}