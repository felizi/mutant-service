package net.felizi.mutant.config.specification;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public final class SpecificationFactory {

  private SpecificationFactory() {
    super();
  }

  public static <D> SpecificationStatus isSatisfiedBy(D domain, List<Specification<D>> specifications) {
    SpecificationStatus status = new SpecificationStatus();
    if (CollectionUtils.isNotEmpty(specifications)) {
      for (Specification<D> specification : specifications) {
        if (!specification.predicate().test(domain)) {
          status.dissatisfy();
          status.addErrors(specification.getError());
        }
      }
    }
    return status;
  }
}