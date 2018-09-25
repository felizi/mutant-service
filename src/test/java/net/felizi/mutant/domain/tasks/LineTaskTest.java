package net.felizi.mutant.domain.tasks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;
import net.felizi.mutant.domain.tasks.LineTask;

@RunWith(SpringRunner.class)
public class LineTaskTest {
  GeneticMatrixDTO firstColumnMutant = null;
  GeneticMatrixDTO mutantEverywhereExceptDiagonal = null;
  GeneticMatrixDTO mutantDiagonalLeft = null;
  GeneticMatrixDTO mutantDiagonalRight = null;
  GeneticMatrixDTO mutantLine = null;

  @Before
  public void setup() {
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

  @Test
  public void shouldNotFoundMutantOnFirstColumn() throws Exception {
    LineTask task = new LineTask(firstColumnMutant);
    Assert.assertEquals(GeneticTypeEnum.HUMAN, task.call());
  }

  @Test
  public void shouldNotFoundMutantOutOfDiagonal() throws Exception {
    LineTask task = new LineTask(mutantEverywhereExceptDiagonal);
    Assert.assertEquals(GeneticTypeEnum.HUMAN, task.call());
  }

  @Test
  public void shouldNotFoundMutantOnDiagonalLeft() throws Exception {
    LineTask task = new LineTask(mutantDiagonalLeft);
    Assert.assertEquals(GeneticTypeEnum.HUMAN, task.call());
  }

  @Test
  public void shouldNotFoundMutantOnDiagonalRight() throws Exception {
    LineTask task = new LineTask(mutantDiagonalRight);
    Assert.assertEquals(GeneticTypeEnum.HUMAN, task.call());
  }

  @Test
  public void shouldFoundMutantOnLine() throws Exception {
    LineTask task = new LineTask(mutantLine);
    Assert.assertEquals(GeneticTypeEnum.MUTANT, task.call());
  }

}