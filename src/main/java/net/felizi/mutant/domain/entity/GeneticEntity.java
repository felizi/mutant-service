package net.felizi.mutant.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.felizi.mutant.domain.enums.GeneticTypeEnum;

@Entity
public class GeneticEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "genetic_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private GeneticTypeEnum type;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GeneticTypeEnum getType() {
    return type;
  }

  public void setType(GeneticTypeEnum type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "GeneticEntity [id=" + id + ", type=" + type + "]";
  }

}
