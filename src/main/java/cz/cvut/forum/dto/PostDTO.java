package cz.cvut.forum.dto;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

public class PostDTO extends AbstractDTO {
    private String title;
    private String content;

    private Long author;
    private Long topicId;

    @XmlTransient
    private UserDTO user;
    @XmlTransient
    private TopicDTO topic;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String content, UserDTO author, TopicDTO topic, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = author;
        this.topic = topic;
//        this.created = created;
//        this.updated = updated;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public TopicDTO getTopic() {
        return topic;
    }

    public void setTopic(TopicDTO topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}