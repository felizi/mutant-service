package net.felizi.mutant.domain.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.config.exception.MultipleException;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IdentifyTypeOfDNAServiceTest {
  private @Autowired IdentifyTypeOfDNAService service;
  private @Autowired StatisticsOfGeneticTypesService statisticsService;

  private GeneticMatrixDTO matrxi6x6 = null;
  private GeneticMatrixDTO matrix5x5 = null;
  private GeneticMatrixDTO empty = null;
  private GeneticMatrixDTO mutantEverywhereExceptDiagonal = null;
  private GeneticMatrixDTO mutantDiagonalLeft = null;
  private GeneticMatrixDTO mutantDiagonalRight = null;
  private GeneticMatrixDTO mutantLine = null;
  private GeneticMatrixDTO incorrectDNA = null;
  private GeneticMatrixDTO mutant = null;
  private GeneticMatrixDTO human = null;

  @Before
  public void setup() {
    matrxi6x6 = new GeneticMatrixDTO(new char[][] { charArray("GGATTC"), charArray("CCTAAG"), charArray("CTGTTA"),
        charArray("TCCTGT"), charArray("AGTAAT"), charArray("CACCTG") });
    matrix5x5 = new GeneticMatrixDTO(new char[][] { charArray("AAAAA"), charArray("AAAAA"), charArray("AAAAA"),
        charArray("AAAAA"), charArray("AAAAA") });
    empty = new GeneticMatrixDTO(new char[][] {});

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
        new char[] { 'A', 'T', 'A', 'T', 'A', 'A' }, //
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

    incorrectDNA = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'X', 'X', 'X', 'X', 'X', 'X' }, //
        new char[] { 'X', 'X', 'X', 'X', 'X', 'X' }, //
        new char[] { 'X', 'X', 'X', 'X', 'X', 'X' }, //
        new char[] { 'X', 'X', 'X', 'X', 'X', 'X' }, //
        new char[] { 'X', 'X', 'X', 'X', 'X', 'X' }, //
        new char[] { 'X', 'X', 'X', 'X', 'X', 'X' }, });

  }

  private char[] charArray(String vlr) {
    return vlr.toCharArray();
  }

  @Test
  public void shouldIncrementHumanWhenHuman() throws Exception {
    long humans = statisticsService.execute().getCountHumanDna();
    Assert.assertEquals(GeneticTypeEnum.HUMAN, service.execute(human));
    Assert.assertEquals(humans + 1, statisticsService.execute().getCountHumanDna());
  }

  @Test
  public void shouldIncrementMutantWhenMutant() throws Exception {
    long mutants = statisticsService.execute().getCountMutantDna();
    Assert.assertEquals(GeneticTypeEnum.MUTANT, service.execute(mutant));
    Assert.assertEquals(mutants + 1, statisticsService.execute().getCountMutantDna());
  }

  @Test
  public void shouldMutantWhenMutantFirstCol() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.MUTANT, service.execute(mutant));
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
  public void shouldMutantWhenCorrectSize6x6() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.HUMAN, service.execute(matrxi6x6));
  }

  @Test
  public void shouldReturnMutantWhen5x5() throws Exception {
    Assert.assertEquals(GeneticTypeEnum.MUTANT, service.execute(matrix5x5));
  }

  @Test(expected = MultipleException.class)
  public void shouldThrowExcpetionWhenEmpty() throws Exception {
    Assert.assertEquals(null, service.execute(empty));
  }

  @Test(expected = MultipleException.class)
  public void shouldThrowExcpetionWhenNull() throws Exception {
    service.execute(null);
  }

  @Test(expected = MultipleException.class)
  public void shouldThrowExcpetionWhenIncorrectDNA() throws Exception {
    service.execute(incorrectDNA);
  }

}