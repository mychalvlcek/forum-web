package cz.cvut.forum.helper;

import cz.cvut.forum.dto.TopicDTO;
import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;

public final class PushEncoder implements Encoder<TopicDTO, String> {

    @Override
    public String encode(TopicDTO topic) {
        PushMessage msg = new PushMessage(topic.getId(), topic.getTitle(), topic.getCategory());
        return new JSONObject(msg).toString();
    }
}
