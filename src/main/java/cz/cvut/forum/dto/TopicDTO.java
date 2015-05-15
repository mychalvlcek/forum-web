package cz.cvut.forum.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


public class TopicDTO extends AbstractDTO {
    private String title;
    private Long author;
    private UserDTO user;
    private Long category;
    private List<Long> posts;

    public TopicDTO() {
    }

    public TopicDTO(Long id, String title, Long author, Long category, List<Long> posts, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.posts = posts;
//        this.created = created;
//        this.updated = updated;
    }

    public TopicDTO(Long id, String title, Long author, UserDTO user, Long category, List<Long> posts, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.user = user;
        this.category = category;
        this.posts = posts;
//        this.created = created;
//        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public List<Long> getPosts() {
        return posts;
    }

    public void setPosts(List<Long> posts) {
        this.posts = posts;
    }

    public int getPostsCount() {
        return this.posts.size();
    }

}