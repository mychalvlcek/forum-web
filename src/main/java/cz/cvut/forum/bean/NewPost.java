package cz.cvut.forum.bean;

import cz.cvut.forum.dto.PostDTO;
import cz.cvut.forum.service.*;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class NewPost {

    private Long id;
    private String title;
    private String content;
    private Long topicId;

    private PostDTO post;

    private PostService postService;
    private TopicService topicService;

    @ManagedProperty(value = "#{user}")
    LoggedUser user;

    @PostConstruct
    public void postConstruct() {
        this.postService = new PostServiceImpl();
        this.topicService = new TopicServiceImpl();
    }

    public void init() throws Exception {
        post = postService.get(id);
        this.title = post.getTitle();
        this.content = post.getContent();
        this.topicId = post.getTopic().getId();
    }

    public void storePost() throws IOException {
//        postService.updatePost(id, title, content, post.getAuthor().getId(), topicId);
//        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Post", "Byl úspěšně upraven."));
//        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + topicId + "&update=1");

        PostDTO rec = new PostDTO();
        rec.setTitle(title);
        rec.setContent(content);
        rec.setAuthor(user.getId());
        rec.setTopicId(topicId);

        postService.save(rec);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public LoggedUser getUser() {
        return user;
    }

    public void setUser(LoggedUser user) {
        this.user = user;
    }

}