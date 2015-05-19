package cz.cvut.forum.messaging;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/actions")
public class EventHandler {

    @OnMessage(encoders = JSONEncoder.class)
    public String onMessage(String message) {
        return message;
    }
}
