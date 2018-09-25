package net.felizi.mutant.config.specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpecificationStatus implements Serializable {
  private static final long serialVersionUID = 1L;

  private boolean satisfied = true;
  private List<net.felizi.mutant.config.exception.ErrorSpec> errors = new ArrayList<>();

  public List<net.felizi.mutant.config.exception.ErrorSpec> getErrors() {
    return errors;
  }

  public void addErrors(net.felizi.mutant.config.exception.ErrorSpec error) {
    errors.add(error);
  }

  public void dissatisfy() {
    this.satisfied = false;
  }

  public boolean isSatisfied() {
    return satisfied;
  }

}