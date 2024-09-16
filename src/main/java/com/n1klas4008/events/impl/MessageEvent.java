package com.n1klas4008.events.impl;

import com.n1klas4008.events.BaseEvent;
import com.n1klas4008.events.Event;
import com.n1klas4008.user.UserMetadata;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MessageEvent extends Event {
    private final String message, channel, type, serverName, nickName, host;
    private UserMetadata userMetadata;

    public MessageEvent(BaseEvent base) {
        super(base);
        this.message = data[data.length - 1].substring(1)
                .replace("\uDB40\uDC00", "") //7TV
                .trim();
        this.channel = data[data.length - 2];
        this.type = data[data.length - 3];
        String[] prefix = data[data.length - 4].split("@");
        this.host = prefix[1];
        String[] names = prefix[0].split("!");
        this.serverName = names[0].substring(1);
        this.nickName = names[1];
        if (!data[0].startsWith("@")) return;
        Map<String, String> tags = getMessageTags();
        this.userMetadata = new UserMetadata(tags);
    }

    private Map<String, String> getMessageTags() {
        Map<String, String> tags = new HashMap<>();
        String[] metadata = data[0].substring(1).split(";");
        for (String pair : metadata) {
            String[] values = pair.split("=");
            tags.put(values[0], values.length == 2 ? values[1] : "");
        }
        return tags;
    }

    public void respond(String message) throws IOException {
        bot.getConnection().sendRAW(
                String.format(
                        "PRIVMSG %s :%s\n",
                        channel,
                        message
                )
        );
    }

    public void reply(String message) throws IOException {
        bot.getConnection().sendRAW(
                String.format(
                        "PRIVMSG %s :@%s %s\n",
                        channel,
                        userMetadata.displayName(),
                        message
                )
        );
    }

    public Optional<UserMetadata> getUserMetadata() {
        return Optional.ofNullable(userMetadata);
    }

    public String getMessage() {
        return message;
    }

    public String getChannel() {
        return channel;
    }

    public String getServerName() {
        return serverName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String getType() {
        return type;
    }
}
