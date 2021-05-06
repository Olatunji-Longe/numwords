package com.numwords.common;

/**
 * @author Olatunji O. Longe
 * @since 05 May, 2021, 7:39 p.m.
 */
public enum Checkpoint {

    QUINTILLION(1000000000000000000L),
    QUADRILLION(1000000000000000L),
    TRILLION(1000000000000L),
    BILLION(1000000000),
    MILLION(1000000),
    THOUSAND(1000),
    HUNDRED(100);

    private final long value;

    Checkpoint(long value) {
        this.value = value;
    }

    public long value() {
        return this.value;
    }

    public String word() {
        return this.name().toLowerCase();
    }
}
