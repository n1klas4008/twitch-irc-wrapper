package com.n1klas4008.events;

public interface EventHandler<T extends Event> {
    void onEvent(T t);
}
