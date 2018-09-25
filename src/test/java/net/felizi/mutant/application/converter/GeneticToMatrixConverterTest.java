package net.felizi.mutant.application.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.application.dto.GeneticDTO;
import net.felizi.mutant.application.dto.GeneticMatrixDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GeneticToMatrixConverterTest {

  private @Autowired GeneticToMatrixConverter geneticToMatrixConverter;

  private GeneticDTO genetic = null;

  @Before
  public void setup() {
    genetic = new GeneticDTO();
    genetic.setDna(new String[] { "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA" });
  }

  @Test
  public void shouldReturnNullDNAWhenNullParameter() {
    GeneticMatrixDTO result = geneticToMatrixConverter.execute(null);
    Assert.assertNotNull(result);
    Assert.assertNull(result.getDna());
  }

  @Test
  public void shouldReturnDNAWhenGeneticParameter() {
    GeneticMatrixDTO result = geneticToMatrixConverter.execute(genetic);
    Assert.assertNotNull(result);
    Assert.assertNotNull(result.getDna());
  }
}
