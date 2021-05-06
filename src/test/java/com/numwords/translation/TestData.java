package com.numwords.translation;

import java.util.stream.Stream;

/**
 * @author Olatunji O. Longe
 * @since 05 May, 2021, 8:21 p.m.
 */
public final class TestData {

    private static Stream<String> nonNumberStrings() {
        return Stream.of(null, "", " ", "   ", "A", "4B", "#$", "0X", "7.4.808", "1.6F", "6E18", "--26", "- 26", "+2400", "+ 20", "++109.45");
    }
}
