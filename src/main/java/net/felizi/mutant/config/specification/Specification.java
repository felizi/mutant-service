package net.felizi.mutant.config.specification;

import java.util.function.Predicate;

public interface Specification<D> {
  public Predicate<D> predicate();

  public net.felizi.mutant.config.exception.ErrorSpec getError();
}