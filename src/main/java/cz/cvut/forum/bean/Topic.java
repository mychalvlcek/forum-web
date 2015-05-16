package cz.cvut.forum.bean;

import cz.cvut.forum.dto.CategoryDTO;
import cz.cvut.forum.dto.PostDTO;
import cz.cvut.forum.dto.TopicDTO;
import cz.cvut.forum.helper.FacesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

//    @Autowired
//    protected LoggedUser user;
//    @Autowired
//    protected CategoryService categoryService;
//    @Autowired
//    protected PostService postService;
//    @Autowired
//    protected TopicService topicService;

    public void init() throws Exception {
        try {
//            topic = topicService.getTopicById(topicId);
        } catch(Exception e) {
            throw new Exception("Tema s id: " + topicId + " nenalezeno.");
        }
    }

    public void storePost() throws IOException {
        topicId = Long.parseLong(FacesUtil.getRequestParameter("topic"));
//        postService.addPost(title, content, user.id(), topicId);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Topic", "Byl úspěšně vložen."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + topicId + "&new=1");
    }

    public void deletePost(Long id) throws IOException {
//        postService.deletePost(id);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Příspěvek", "Byl smazán."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + topicId + "&del=1");
    }

    public CategoryDTO getCategory() {
        return new CategoryDTO();
//        return categoryService.getCategoryById(topic.getCategory());
    }

    public List<PostDTO> getPosts() {
        return new ArrayList<PostDTO>();
//        return postService.getPostsByTopic(topicId);
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