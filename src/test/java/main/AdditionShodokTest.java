package main;

import org.junit.jupiter.api.Test;

import static main.ShadokNumber.newShadokNumber;
import static main.ShadokNumeral.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AdditionShodokTest {
  @Test
  void X_plus_GA_egal_X() {
    assertAddition(newShadokNumber(GA), newShadokNumber(GA), newShadokNumber(GA));
    assertAddition(newShadokNumber(BU), newShadokNumber(GA), newShadokNumber(BU));
    assertAddition(newShadokNumber(ZO), newShadokNumber(GA), newShadokNumber(ZO));
    assertAddition(newShadokNumber(MEU), newShadokNumber(GA), newShadokNumber(MEU));
  }

  @Test
  void given_X_numeral_X_plus_BU_egal_successeur_de_X() {
    assertAddition(newShadokNumber(GA), newShadokNumber(BU), newShadokNumber(BU));
    assertAddition(newShadokNumber(BU), newShadokNumber(BU), newShadokNumber(ZO));
    assertAddition(newShadokNumber(ZO), newShadokNumber(BU), newShadokNumber(MEU));
    assertAddition(newShadokNumber(MEU), newShadokNumber(BU), newShadokNumber(BU, GA));
  }

  @Test
  void given_X_number_X_plus_BU_egal_successeur_de_X() {
    assertAddition(newShadokNumber(BU, GA), newShadokNumber(BU), newShadokNumber(BU, BU));
    assertAddition(newShadokNumber(BU, BU), newShadokNumber(BU), newShadokNumber(BU, ZO));
    assertAddition(newShadokNumber(BU, ZO), newShadokNumber(BU), newShadokNumber(BU, MEU));
    assertAddition(newShadokNumber(BU, MEU), newShadokNumber(BU), newShadokNumber(ZO, GA));

    assertAddition(newShadokNumber(MEU, GA), newShadokNumber(BU), newShadokNumber(MEU, BU));
    assertAddition(newShadokNumber(MEU, BU), newShadokNumber(BU), newShadokNumber(MEU, ZO));
    assertAddition(newShadokNumber(MEU, ZO), newShadokNumber(BU), newShadokNumber(MEU, MEU));
    assertAddition(newShadokNumber(MEU, MEU), newShadokNumber(BU), newShadokNumber(BU, GA, GA));

    assertAddition(
        newShadokNumber(MEU, MEU, GA), newShadokNumber(BU), newShadokNumber(MEU, MEU, BU));
    assertAddition(
        newShadokNumber(MEU, MEU, BU), newShadokNumber(BU), newShadokNumber(MEU, MEU, ZO));
    assertAddition(
        newShadokNumber(MEU, MEU, ZO), newShadokNumber(BU), newShadokNumber(MEU, MEU, MEU));
    assertAddition(
        newShadokNumber(MEU, MEU, MEU), newShadokNumber(BU), newShadokNumber(BU, GA, GA, GA));

    assertAddition(
        newShadokNumber(MEU, BU, MEU, MEU, GA),
        newShadokNumber(BU),
        newShadokNumber(MEU, BU, MEU, MEU, BU));
    assertAddition(
        newShadokNumber(MEU, BU, MEU, MEU, BU),
        newShadokNumber(BU),
        newShadokNumber(MEU, BU, MEU, MEU, ZO));
    assertAddition(
        newShadokNumber(MEU, BU, MEU, MEU, ZO),
        newShadokNumber(BU),
        newShadokNumber(MEU, BU, MEU, MEU, MEU));
    assertAddition(
        newShadokNumber(MEU, BU, MEU, MEU, MEU),
        newShadokNumber(BU),
        newShadokNumber(MEU, ZO, GA, GA, GA));
  }

  @Test
  void testPredecessor() {
    assertThat(newShadokNumber(BU).getPredecessor()).isEqualTo(GA.toNumber());
    assertThat(newShadokNumber(ZO).getPredecessor()).isEqualTo(BU.toNumber());
    assertThat(newShadokNumber(MEU).getPredecessor()).isEqualTo(ZO.toNumber());
    assertThat(newShadokNumber(BU, GA).getPredecessor()).isEqualTo(MEU.toNumber());

    assertThat(newShadokNumber(BU, GA).getPredecessor()).isEqualTo(newShadokNumber(MEU));
    assertThat(newShadokNumber(BU, BU).getPredecessor()).isEqualTo(newShadokNumber(BU, GA));
    assertThat(newShadokNumber(BU, ZO).getPredecessor()).isEqualTo(newShadokNumber(BU, BU));
    assertThat(newShadokNumber(BU, MEU).getPredecessor()).isEqualTo(newShadokNumber(BU, ZO));

    assertThat(newShadokNumber(BU, GA, GA).getPredecessor()).isEqualTo(newShadokNumber(MEU, MEU));
    assertThat(newShadokNumber(BU, GA, GA, GA, GA).getPredecessor())
        .isEqualTo(newShadokNumber(MEU, MEU, MEU, MEU));
  }

  @Test
  void BUGA_concat_ZOMEU_egal_BUGAZOMEU() {
    ShadokNumber concat = newShadokNumber(BU, GA).concat(newShadokNumber(ZO, MEU));
    System.out.println(concat);
    assertThat(concat).isEqualTo(newShadokNumber(BU, GA, ZO, MEU));
  }

  @Test
  void last_significant_numeral_of_BUGAZO_is_ZO() {
    assertThat(newShadokNumber(BU, GA, ZO).getLeastSignificantNumeral()).isEqualTo(ZO);
  }

  @Test
  void BUGAZO_shiftRight_equals_BUGA() {
    assertThat(newShadokNumber(BU, GA, ZO).shiftRight()).isEqualTo(newShadokNumber(BU, GA));
  }

  @Test
  void GA_plus_ZO_egal_ZO() {
    assertAddition(GA.toNumber(), ZO.toNumber(), ZO.toNumber());
  }

  @Test
  void ZOMEU_plus_MEUGA_egal_BUBUMEU() {
    assertAddition(
        newShadokNumber(ZO, MEU), newShadokNumber(MEU, GA), newShadokNumber(BU, BU, MEU));
  }

  private void assertAddition(ShadokNumber op1, ShadokNumber op2, ShadokNumber expected) {
    assertThat(ShadokArithmetics.add(op1, op2)).isEqualTo(expected);
  }
}
