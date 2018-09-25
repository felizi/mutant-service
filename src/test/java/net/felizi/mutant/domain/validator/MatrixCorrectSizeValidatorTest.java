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
public class MatrixCorrectSizeValidatorTest {
  private @Autowired MatrixCorrectSizeValidator validator;

  private GeneticMatrixDTO matrxi6x6;
  private GeneticMatrixDTO matrix5x5;
  private GeneticMatrixDTO matrix2x5;
  private GeneticMatrixDTO empty;
  private GeneticMatrixDTO matrixDifferentSizr;

  @Before
  public void setup() {
    matrxi6x6 = new GeneticMatrixDTO(new char[][] { charArray("GGATTC"), charArray("CCTAAG"), charArray("CTGTTA"),
        charArray("TCCTGT"), charArray("AGTAAT"), charArray("CACCTG") });
    matrix5x5 = new GeneticMatrixDTO(new char[][] { charArray("AAAAA"), charArray("AAAAA"), charArray("AAAAA"),
        charArray("AAAAA"), charArray("AAAAA") });
    matrix2x5 = new GeneticMatrixDTO(
        new char[][] { charArray("XX"), charArray("XX"), charArray("XX"), charArray("XX"), charArray("XX") });
    empty = new GeneticMatrixDTO(new char[][] {});
    matrixDifferentSizr = new GeneticMatrixDTO(
        new char[][] { charArray("AA"), charArray("AAA"), charArray("AAAA"), charArray("AAAAA"), charArray("AAAAAA") });
  }

  private char[] charArray(String vlr) {
    return vlr.toCharArray();
  }

  @Test
  public void shouldTrueWhenCorrectSize6x6() throws Exception {
    Assert.assertTrue(validator.predicate().test(matrxi6x6));
  }

  @Test
  public void shouldFalseWhen5x5() throws Exception {
    Assert.assertFalse(validator.predicate().test(matrix5x5));
  }

  @Test
  public void shouldFalseWhen2x5() throws Exception {
    Assert.assertFalse(validator.predicate().test(matrix2x5));
  }

  @Test
  public void shouldFalseWhenEmpty() throws Exception {
    Assert.assertFalse(validator.predicate().test(empty));
  }

  @Test
  public void shouldFalseWhenDifSize() throws Exception {
    Assert.assertFalse(validator.predicate().test(matrixDifferentSizr));
  }
}