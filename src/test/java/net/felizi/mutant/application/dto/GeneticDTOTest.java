package net.felizi.mutant.application.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GeneticDTOTest {

  @Test
  public void shouldReturnNullWhenNullParameter() {
    GeneticDTO genetic = new GeneticDTO(null);
    Assert.assertNotNull(genetic);
    Assert.assertNull(genetic.getDna());
    Assert.assertFalse(genetic.hasDNA());
  }

  @Test
  public void shouldReturnNotNullWhenValidParameter() {
    GeneticDTO genetic = new GeneticDTO(new String[] { "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA" });
    Assert.assertNotNull(genetic);
    Assert.assertNotNull(genetic.getDna());
    Assert.assertTrue(genetic.hasDNA());
  }

  @Test
  public void shouldReturnEmptyDNAWhenGeneticParameter() {
    GeneticDTO genetic = new GeneticDTO(new String[] {});
    Assert.assertNotNull(genetic);
    Assert.assertNotNull(genetic.getDna());
    Assert.assertFalse(genetic.hasDNA());
  }
}
