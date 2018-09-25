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
public class IdentifyTypeOfDNAParallelExecutorServiceTest {
  private @Autowired IdentifyTypeOfDNAParallelExecutorService service;

  private GeneticMatrixDTO matrxi6x6 = null;
  private GeneticMatrixDTO matrix5x5 = null;
  private GeneticMatrixDTO empty = null;
  private GeneticMatrixDTO firstColumnMutant = null;
  private GeneticMatrixDTO mutantEverywhereExceptDiagonal = null;
  private GeneticMatrixDTO mutantDiagonalLeft = null;
  private GeneticMatrixDTO mutantDiagonalRight = null;
  private GeneticMatrixDTO mutantLine = null;

  @Before
  public void setup() {
    matrxi6x6 = new GeneticMatrixDTO(new char[][] { charArray("GGATTC"), charArray("CCTAAG"), charArray("CTGTTA"),
        charArray("TCCTGT"), charArray("AGTAAT"), charArray("CACCTG") });
    matrix5x5 = new GeneticMatrixDTO(new char[][] { charArray("AAAAA"), charArray("AAAAA"), charArray("AAAAA"),
        charArray("AAAAA"), charArray("AAAAA") });
    empty = new GeneticMatrixDTO(new char[][] {});

    firstColumnMutant = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'A', 'G', 'A', 'B', 'A', 'A' }, //
        new char[] { 'A', 'A', 'A', 'G', 'A', 'A' }, //
        new char[] { 'A', 'C', 'G', 'T', 'A', 'C' }, //
        new char[] { 'A', 'A', 'C', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'T', 'C', 'G', 'A' }, //
        new char[] { 'A', 'A', 'T', 'A', 'A', 'A' }, });

    mutantEverywhereExceptDiagonal = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, });

    mutantDiagonalLeft = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'A', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'A', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'G', 'A', 'A', 'A' }, //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, });

    mutantDiagonalRight = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'A', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'A', 'B', 'A', 'A' }, //
        new char[] { 'A', 'T', 'A', 'A', 'C', 'A' }, //
        new char[] { 'A', 'A', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, });

    mutantLine = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'A', 'G', 'C', 'A', 'A' }, //
        new char[] { 'A', 'T', 'A', 'A', 'A', 'A' }, //
        new char[] { 'A', 'T', 'A', 'A', 'A', 'A' }, //
        new char[] { 'A', 'C', 'C', 'C', 'C', 'A' }, //
        new char[] { 'A', 'T', 'G', 'C', 'A', 'A' }, });

  }

  private char[] charArray(String vlr) {
    return vlr.toCharArray();
  }

  @Test
  public void shouldMutantWhenMutantFirstCol() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.MUTANT, service.execute(firstColumnMutant));
  }

  @Test
  public void shouldMutantWhenMutantEverywhere() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.MUTANT, service.execute(mutantEverywhereExceptDiagonal));
  }

  @Test
  public void shouldMutantWhenMutantDiagonalLeft() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.MUTANT, service.execute(mutantDiagonalLeft));
  }

  @Test
  public void shouldMutantWhenMutantDiagonalRight() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.MUTANT, service.execute(mutantDiagonalRight));
  }

  @Test
  public void shouldMutantWhenMutantLine() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.MUTANT, service.execute(mutantLine));
  }

  @Test
  public void shouldHumanWhenCorrectSize6x6() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.HUMAN, service.execute(matrxi6x6));
  }

  @Test
  public void shouldReturnMutantWhen5x5() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.MUTANT, service.execute(matrix5x5));
  }

  @Test
  public void shouldReturnNullWhenEmpty() throws Exception {
    Assert.assertEquals(null, service.execute(empty));
  }

  @Test
  public void shouldReturnNullWhenNull() throws Exception {
    Assert.assertEquals(null, service.execute(null));
  }

}