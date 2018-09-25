package net.felizi.mutant.domain.enums;

public enum NucleotideEnum {
  ADENINE('A'), CYTOSINE('C'), GUANINE('G'), THYMINE('T');

  private final char symbol;

  NucleotideEnum(char symbol) {
    this.symbol = symbol;
  }

  public char getSymbol() {
    return symbol;
  }
}