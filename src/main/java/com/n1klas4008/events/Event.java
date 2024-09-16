package com.n1klas4008.events;

import com.n1klas4008.Bot;

public abstract class Event {
    protected final long timestamp = System.currentTimeMillis();
    protected final String[] data;
    protected final Bot bot;

    public Event(BaseEvent base) {
        this.data = base.data();
        this.bot = base.bot();
    }

    public Bot getBot() {
        return bot;
    }

    public abstract String getType();

    public long getTimestamp() {
        return timestamp;
    }
}
