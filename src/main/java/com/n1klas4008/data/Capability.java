package com.n1klas4008.data;

import java.util.Locale;

public enum Capability {
    COMMANDS, MEMBERSHIP, TAGS;

    @Override
    public String toString() {
        return String.join(
                "/",
                "twitch.tv",
                name().toLowerCase(Locale.ENGLISH)
        );
    }
}
