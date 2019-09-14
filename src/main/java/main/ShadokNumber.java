package main;

import java.util.*;
import java.util.stream.Collectors;

import static main.ShadokNumeral.*;

public class ShadokNumber {
  private List<ShadokNumeral> shadokNumerals;

  private ShadokNumber(Collection<ShadokNumeral> shadokNumerals) {
    this.shadokNumerals = new ArrayList<>(shadokNumerals);
  }

  private ShadokNumber(ShadokNumeral[] shadokNumerals) {
    this.shadokNumerals = Arrays.stream(shadokNumerals).collect(Collectors.toList());
  }

  public static ShadokNumber newShadokNumber(ShadokNumeral... shadokNumerals) {
    return new ShadokNumber(shadokNumerals);
  }

  public ShadokNumber plus(ShadokNumber op2) {
    assert Objects.nonNull(op2);
    if (op2.equals(GA.toNumber())) {
      return this;
    } else {
      return this.plus(op2.getPredecessor()).getSuccessor();
    }
  }

  private ShadokNumber getSuccessor() {
    if (shadokNumerals.size() == 1) {
      if (this.equals(GA.toNumber())) return BU.toNumber();
      else if (this.equals(BU.toNumber())) return ZO.toNumber();
      else if (this.equals(ZO.toNumber())) return MEU.toNumber();
      else return newShadokNumber(BU, GA);
    } else {
      if (this.getLeastSignificantNumeral().equals(GA)) return shiftRight().concat(BU.toNumber());
      else if (this.getLeastSignificantNumeral().equals(BU))
        return shiftRight().concat(ZO.toNumber());
      else if (this.getLeastSignificantNumeral().equals(ZO))
        return shiftRight().concat(MEU.toNumber());
      else return shiftRight().getSuccessor().concat(GA.toNumber());
    }
  }

  public ShadokNumber shiftRight() {
    return new ShadokNumber(shadokNumerals.subList(0, shadokNumerals.size() - 1));
  }

  public ShadokNumber concat(ShadokNumber otherNumber) {
    ShadokNumber shadokNumber = new ShadokNumber(this.shadokNumerals);
    shadokNumber.shadokNumerals.addAll(otherNumber.shadokNumerals);
    return shadokNumber;
  }

  public ShadokNumeral getLeastSignificantNumeral() {
    return shadokNumerals.get(shadokNumerals.size() - 1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShadokNumber that = (ShadokNumber) o;
    return Objects.equals(shadokNumerals, that.shadokNumerals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shadokNumerals);
  }

  @Override
  public String toString() {
    return "ShadokNumber{" + "shadokNumerals=" + shadokNumerals + '}';
  }

  public ShadokNumber getPredecessor() {
    if (this.equals(BU.toNumber())) return GA.toNumber();
    else if (this.equals(ZO.toNumber())) return BU.toNumber();
    else if (this.equals(MEU.toNumber())) return ZO.toNumber();
    else if (this.equals(newShadokNumber(BU, GA))) return MEU.toNumber();
    else {
      if (this.getLeastSignificantNumeral().equals(GA))
        return shiftRight().getPredecessor().concat(MEU.toNumber());
      else if (this.getLeastSignificantNumeral().equals(BU))
        return shiftRight().concat(GA.toNumber());
      else if (this.getLeastSignificantNumeral().equals(ZO))
        return shiftRight().concat(BU.toNumber());
      else return shiftRight().concat(ZO.toNumber());
    }
  }
}
