package net.felizi.mutant.domain.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import net.felizi.mutant.domain.entity.GeneticEntity;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

  private @Autowired TestEntityManager entityManager;

  private @Autowired GeneticRepository repository;

  @Test
  public void whenCountByTypeNullThenReturnZero() {
    // given

    // when
    long count = repository.countByType(null);

    // then
    Assert.assertEquals(count, 0);
  }

  @Test
  public void whenCountByTypeHumanThenReturnZero() {
    // given

    // when
    long count = repository.countByType(GeneticTypeEnum.HUMAN);

    // then
    Assert.assertEquals(count, 0);
  }

  @Test
  public void whenCountByTypeMutantThenReturnOne() {
    // given
    GeneticEntity genetic = new GeneticEntity();
    genetic.setType(GeneticTypeEnum.MUTANT);
    entityManager.persist(genetic);
    entityManager.flush();

    // when
    long count = repository.countByType(genetic.getType());

    // then
    Assert.assertEquals(count, 1);
  }

}
