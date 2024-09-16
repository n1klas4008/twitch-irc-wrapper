package com.n1klas4008.user;

public record BadgeInfo(String name, String value) {
    public BadgeInfo(String[] pair) {
        this(
                pair[0],
                pair[1]
        );
    }
}
