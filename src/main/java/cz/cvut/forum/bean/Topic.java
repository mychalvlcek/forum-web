package cz.cvut.forum.bean;

import cz.cvut.forum.dto.CategoryDTO;
import cz.cvut.forum.dto.PostDTO;
import cz.cvut.forum.dto.TopicDTO;
import cz.cvut.forum.helper.FacesUtil;
import cz.cvut.forum.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class Topic {

    private Long topicId;
    private TopicDTO topic;

    private String title;
    private String content;

    private CategoryService categoryService;
    private TopicService topicService;
    private PostService postService;

    @PostConstruct
    public void postConstruct() {
        this.categoryService = new CategoryServiceImpl();
        this.topicService = new TopicServiceImpl();
        this.postService = new PostServiceImpl();
    }

    public void init() throws Exception {
        topic = topicService.get(topicId);
    }

    public void storePost() throws IOException {
        topicId = Long.parseLong(FacesUtil.getRequestParameter("topic"));
        PostDTO post = new PostDTO();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(6L);
        post.setTopicId(topicId);
        postService.save(post);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Topic", "Byl úspěšně vložen."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + topicId + "&new=1");
    }

    public void deletePost(Long id) throws IOException {
        postService.delete(id);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Příspěvek", "Byl smazán."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + topicId + "&del=1");
    }

    public CategoryDTO getCategory() {
        return categoryService.get(topic.getCategory());
    }

    public List<PostDTO> getPosts() {
        return postService.getByTopic(topicId);
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long id) {
        this.topicId = id;
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