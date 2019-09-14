package main;

import static main.ShadokNumber.newShadokNumber;

public enum ShadokNumeral {
  GA,
  BU,
  ZO,
  MEU;

  static {
    GA.number = newShadokNumber(GA);
    BU.number = newShadokNumber(BU);
    ZO.number = newShadokNumber(ZO);
    MEU.number = newShadokNumber(MEU);
  }

  private ShadokNumber number;

  public ShadokNumber toNumber() {
    return number;
  }
}
