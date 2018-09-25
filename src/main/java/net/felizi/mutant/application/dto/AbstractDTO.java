package net.felizi.mutant.application.dto;

import java.io.Serializable;

public abstract class AbstractDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  public abstract String toString();
}