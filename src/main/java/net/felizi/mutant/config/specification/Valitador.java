package net.felizi.mutant.config.specification;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.felizi.mutant.config.exception.MultipleException;

public class Valitador<D> {
  private static final Logger LOGGER = LoggerFactory.getLogger(Valitador.class);
  protected final List<Specification<D>> specifications;

  @SuppressWarnings("unchecked")
  public Valitador(Specification<D>... specifications) {
    this.specifications = Arrays.asList(specifications);
  }

  private SpecificationStatus isSatisfiedBy(D d) {
    return SpecificationFactory.isSatisfiedBy(d, specifications);
  }

  public void validade(D d) {
    SpecificationStatus status = isSatisfiedBy(d);
    if (!status.isSatisfied()) {
      LOGGER.error("Errors={}", status.getErrors());
      throw new MultipleException(status.getErrors());
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static <D> Valitador<D> of(Specification<D>... specifications) {
    return new Valitador(specifications);
  }
}