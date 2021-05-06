package com.numwords.translation;

import com.numwords.common.Checkpoint;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.numwords.common.Dictionary.DOUBLE_DIGIT_TENS;
import static com.numwords.common.Dictionary.TEENS;
import static com.numwords.common.Dictionary.UNITS;
import static com.numwords.common.Dictionary.ZERO;

/**
 * @author Olatunji O. Longe
 * @since 05 May, 2021, 7:52 p.m.
 */
public class Translator {

    private final List<Checkpoint> checkpoints;

    public Translator() {
        this.checkpoints = Stream.of(Checkpoint.values()).collect(Collectors.toList());
    }

    public String toWords(final String number) {
        BigDecimal num = validate(number);

        long cardinal = num.longValue();
        String fractional = StringUtils.substringAfterLast(number, ".");

        String words = StringUtils.capitalize(cardinal == 0 ? ZERO : translate(cardinal).trim());
        words = number.startsWith("-") ? String.format("Negative %s", words) : words;

        String decimalWords = toDecimalWords(fractional);
        return decimalWords.isEmpty() ? words : String.format("%s point%s", words, decimalWords);
    }

    private BigDecimal validate(String number) {
        if (!NumberUtils.isParsable(number)) {
            throw new NumberFormatException(String.format("%s is not a valid number", number));
        }

        BigDecimal numberDecimal = new BigDecimal(String.valueOf(number));
        if (numberDecimal.compareTo(BigDecimal.ZERO) >= 0) {
            return numberDecimal;
        }else {
            return numberDecimal.multiply(BigDecimal.valueOf(-1));
        }
    }

    private String toDecimalWords(String decimalPart) {
        String decimalWords = "";
        for (char character : decimalPart.toCharArray()) {
            int digit = Character.getNumericValue(character);
            decimalWords = String.format("%s %s", decimalWords, (digit == 0 ? ZERO : UNITS[digit - 1]));
        }
        return decimalWords;
    }

    private String translate(long number) {
        for (Checkpoint checkpoint : Checkpoint.values()) {
            if (number >= checkpoint.value()) {
                return translate100Up(number, checkpoint);
            }
        }
        return translate99Down((int) number);
    }

    private String translate100Up(long number, Checkpoint checkpoint) {
        String left = translate(number / checkpoint.value());
        String right = translate(number % checkpoint.value());

        if (shouldIncludeAndWordAfterCheckpoint(checkpoint, right)) {
            return String.format("%s %s and %s", left, checkpoint.word(), right);
        }

        return right.isEmpty() ? String.format("%s %s", left, checkpoint.word()) : String.format("%s %s %s", left, checkpoint.word(), right);
    }

    private String translate99Down(int number) {
        int min = 0, max = 99;

        if (number < min || number > max) {
            throw new IllegalArgumentException(
                    String.format("number %s not allowed - valid value must be an integer between %s and %s inclusive", number, min, max)
            );
        }

        if (number == 0) {
            return "";
        } else if (number <= 9) {
            return UNITS[number - 1];
        } else if (number <= 19) {
            return TEENS[number % 10];
        } else {
            String left = DOUBLE_DIGIT_TENS[(number / 10) - 1];
            String right = translate99Down(number % 10);
            return right.isEmpty() ? String.format("%s", left) : String.format("%s %s", left, right);
        }
    }

    private boolean shouldIncludeAndWordAfterCheckpoint(Checkpoint checkpoint, String right) {
        if (checkpoint.equals(Checkpoint.HUNDRED)) {
            return !right.isEmpty();
        } else {
            return checkpoints.stream()
                    .skip(checkpoints.indexOf(checkpoint) + 1)
                    .noneMatch(cp -> right.contains(cp.word()));
        }
    }

}
