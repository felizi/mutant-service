package net.felizi.mutant.application.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GeneticMatrixDTOTest {

  @Test
  public void shouldReturnNullWhenNullParameter() {
    GeneticMatrixDTO mtx = new GeneticMatrixDTO(null);
    Assert.assertNotNull(mtx);
    Assert.assertNull(mtx.getDna());
    Assert.assertFalse(mtx.hasDNA());
  }

  @Test
  public void shouldReturnNotNullWhenValidParameter() {
    GeneticMatrixDTO mtx = new GeneticMatrixDTO(
        new char[][] { new char[] { 'A', 'A', 'A', 'A' }, new char[] { 'A', 'A', 'A', 'A' },
            new char[] { 'A', 'A', 'A', 'A' }, new char[] { 'A', 'A', 'A', 'A' }, new char[] { 'A', 'A', 'A', 'A' } });
    Assert.assertNotNull(mtx);
    Assert.assertNotNull(mtx.getDna());
    Assert.assertTrue(mtx.hasDNA());
  }

  @Test
  public void shouldReturnEmptyDNAWhenGeneticParameter() {
    GeneticMatrixDTO mtx = new GeneticMatrixDTO(new char[][] {});
    Assert.assertNotNull(mtx);
    Assert.assertNotNull(mtx.getDna());
    Assert.assertFalse(mtx.hasDNA());
  }
}
