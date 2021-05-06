package com.numwords.translation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Olatunji O. Longe
 * @since 05 May, 2021, 7:53 p.m.
 */
public class TranslatorTest {

    private Translator translator;

    @BeforeEach
    void setup() {
        translator = new Translator();
    }

    @ParameterizedTest
    @MethodSource("com.numwords.translation.TestData#nonNumberStrings")
    @DisplayName("NumberFormatException is thrown when argument is not a valid number")
    public void throwExceptionWhenInvalidNumber(String string) {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () ->
                translator.toWords(string)
        );
        assertEquals(String.format("%s is not a valid number", string), exception.getMessage());
    }

    @Test
    @DisplayName("0 is translated correctly")
    public void translateZero() {
        assertEquals("Zero", translator.toWords("0"));
    }

    @Test
    @DisplayName("13 is translated correctly")
    public void translate13() {
        assertEquals("Thirteen", translator.toWords("13"));
    }

    @Test
    @DisplayName("85 is translated correctly")
    public void translate85() {
        assertEquals("Eighty five", translator.toWords("85"));
    }

    @Test
    @DisplayName("107 is translated correctly")
    public void translate107() {
        assertEquals("One hundred and seven", translator.toWords("107"));
    }

    @Test
    @DisplayName("411 is translated correctly")
    public void translate411() {
        assertEquals("Four hundred and eleven", translator.toWords("411"));
    }

    @Test
    @DisplayName("943 is translated correctly")
    public void translate983() {
        assertEquals("Nine hundred and forty three", translator.toWords("943"));
    }

    @Test
    @DisplayName("1008 is translated correctly")
    public void translate1008() {
        assertEquals("One thousand and eight", translator.toWords("1008"));
    }

    @Test
    @DisplayName("1065 is translated correctly")
    public void translate1065() {
        assertEquals("One thousand and sixty five", translator.toWords("1065"));
    }

    @Test
    @DisplayName("1111 is translated correctly")
    public void translate1111() {
        assertEquals("One thousand one hundred and eleven", translator.toWords("1111"));
    }

    @Test
    @DisplayName("15101 is translated correctly")
    public void translate15101() {
        assertEquals("Fifteen thousand one hundred and one", translator.toWords("15101"));
    }

    @Test
    @DisplayName("600279 is translated correctly")
    public void translate600279() {
        assertEquals("Six hundred thousand two hundred and seventy nine", translator.toWords("600279"));
    }

    @Test
    @DisplayName("2000012 is translated correctly")
    public void translate2000012() {
        assertEquals("Two million and twelve", translator.toWords("2000012"));
    }

    @Test
    @DisplayName("32074001 is translated correctly")
    public void translate32074001() {
        assertEquals("Thirty two million seventy four thousand and one", translator.toWords("32074001"));
    }

    @Test
    @DisplayName("107009821 is translated correctly")
    public void translate107009821() {
        assertEquals("One hundred and seven million nine thousand eight hundred and twenty one", translator.toWords("107009821"));
    }

    @Test
    @DisplayName("2050456010 is translated correctly")
    public void translate2050456010() {
        assertEquals("Two billion fifty million four hundred and fifty six thousand and ten", translator.toWords("2050456010"));
    }

    @Test
    @DisplayName("Max Integer (2147483647) is translated correctly")
    public void translateMaxInt() {
        assertEquals("Two billion one hundred and forty seven million four hundred and eighty three thousand six hundred and forty seven",
                translator.toWords(BigDecimal.valueOf(Integer.MAX_VALUE).toPlainString()));
    }

    @Test
    @DisplayName("translate negative number when negatives are allowed")
    public void translateNegativeIntWhenAllowed() {
        assertEquals("Negative Ten thousand eight hundred and fourteen", translator.toWords("-10814"));
    }

    @Test
    @DisplayName("translate decimal correctly")
    public void translateDecimal() {
        assertEquals("Eighty six thousand and forty three point nine zero zero nine one four", translator.toWords("86043.900914"));
    }

    @Test
    @DisplayName("translate negative decimal when negatives are allowed")
    public void translateNegativeDecimalWhenAllowed() {
        assertEquals("Negative Zero point nine zero five six", translator.toWords("-0.9056"));
    }

    @Test
    @DisplayName("Max Long (9223372036854775807) is translated correctly")
    public void translateMaxLong() {
        assertEquals("Nine quintillion two hundred and twenty three quadrillion three hundred and seventy two trillion thirty six billion " +
                        "eight hundred and fifty four million seven hundred and seventy five thousand eight hundred and seven",
                translator.toWords(BigDecimal.valueOf(Long.MAX_VALUE).toPlainString()));
    }

    @Test
    @DisplayName("Large Decimal (9223372036854775683.5433) is translated correctly")
    public void translateLargeNumberDecimal() {
        assertEquals("Nine quintillion two hundred and twenty three quadrillion three hundred and seventy two trillion thirty six billion " +
                        "eight hundred and fifty four million seven hundred and seventy five thousand six hundred and eighty three point five four three three",
                translator.toWords(BigDecimal.valueOf(Long.MAX_VALUE).subtract(BigDecimal.valueOf(123.4567)).toPlainString()));
    }

    @Test
    @DisplayName("Large Negative Decimal (-9223372035620207916.037) is translated correctly")
    public void translateLargeNegativeDecimal() {
        assertEquals("Negative Nine quintillion two hundred and twenty three quadrillion three hundred and seventy two trillion thirty five billion " +
                        "six hundred and twenty million two hundred and seven thousand nine hundred and sixteen point zero three seven",
                translator.toWords(BigDecimal.valueOf(1234567890.963).subtract(BigDecimal.valueOf(Long.MAX_VALUE)).toPlainString()));
    }

}
