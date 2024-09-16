package com.n1klas4008.events.impl;

import com.n1klas4008.events.BaseEvent;
import com.n1klas4008.events.Event;

public class UnknownEvent extends Event {
    public UnknownEvent(BaseEvent base) {
        super(base);
    }

    @Override
    public String getType() {
        return "UNKNOWN";
    }
}
