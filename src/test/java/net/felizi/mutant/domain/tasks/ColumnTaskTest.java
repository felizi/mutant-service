package net.felizi.mutant.domain.tasks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;
import net.felizi.mutant.domain.tasks.ColumnTask;

@RunWith(SpringRunner.class)
public class ColumnTaskTest {
  GeneticMatrixDTO firstColumnMutant = null;
  GeneticMatrixDTO mutantEverywhereExceptColumn = null;

  @Before
  public void setup() {
    firstColumnMutant = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'A', 'G', 'A', 'A', 'A', 'A' }, //
        new char[] { 'A', 'A', 'A', 'G', 'A', 'A' }, //
        new char[] { 'A', 'C', 'G', 'T', 'T', 'C' }, //
        new char[] { 'A', 'A', 'C', 'A', 'A', 'A' }, //
        new char[] { 'A', 'T', 'A', 'C', 'G', 'A' }, //
        new char[] { 'A', 'A', 'T', 'A', 'A', 'A' }, });

    mutantEverywhereExceptColumn = new GeneticMatrixDTO(new char[][] { //
        new char[] { 'A', 'A', 'A', 'A', 'A', 'A' }, //
        new char[] { 'A', 'A', 'A', 'A', 'A', 'A' }, //
        new char[] { 'G', 'A', 'T', 'A', 'T', 'C' }, //
        new char[] { 'A', 'C', 'A', 'G', 'A', 'A' }, //
        new char[] { 'A', 'A', 'A', 'A', 'A', 'A' }, //
        new char[] { 'A', 'A', 'A', 'A', 'A', 'A' }, });

  }

  @Test
  public void shouldFoundMutantOnFirstColumn() throws Exception {
    ColumnTask columnTask = new ColumnTask(firstColumnMutant);
    Assert.assertEquals(GeneticTypeEnum.MUTANT, columnTask.call());
  }

  @Test
  public void shouldNotFoundMutantOutColumn() throws Exception {
    ColumnTask columnTask = new ColumnTask(mutantEverywhereExceptColumn);
    Assert.assertEquals(GeneticTypeEnum.HUMAN, columnTask.call());
  }

}