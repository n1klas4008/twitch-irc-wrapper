package com.n1klas4008.events;

import com.n1klas4008.Bot;

public record BaseEvent(Bot bot, String[] data) {

}