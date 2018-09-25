package net.felizi.mutant.domain.repository;

import org.springframework.data.repository.CrudRepository;

import net.felizi.mutant.domain.entity.GeneticEntity;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;

public interface GeneticRepository extends CrudRepository<GeneticEntity, Long> {

  long countByType(GeneticTypeEnum type);

}