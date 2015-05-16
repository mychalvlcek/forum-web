package cz.cvut.forum.bean;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import cz.cvut.forum.dto.CategoryDTO;
import cz.cvut.forum.dto.PostDTO;
import cz.cvut.forum.dto.TopicDTO;
import cz.cvut.forum.dto.UserDTO;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

@ManagedBean
@ViewScoped
public class NewPost {

    private Long id;
    private String title;
    private String content;
    private Long topicId;

    private PostDTO post;

//    @Autowired
//    protected LoggedUser user;
//    @Autowired
//    protected PostService postService;

    public void init() throws Exception {
        ClientResponse r = Client.create()
                .resource("http://localhost:8080/api/post/" + id)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        post = r.getEntity(PostDTO.class);
        this.title = post.getTitle();
        this.content = post.getContent();
        this.topicId = post.getTopic().getId();

//        try {
//            post = postService.getPostById(id);
//            this.title = post.getTitle();
//            this.content = post.getContent();
//            this.topicId = post.getTopic().getId();
//        } catch(Exception e) {
//            throw new Exception("Příspěvek s id: " + id + " nenalezen.");
//        }
    }

    public void storePost() throws IOException {
//        postService.updatePost(id, title, content, post.getAuthor().getId(), topicId);
//        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Post", "Byl úspěšně upraven."));
//        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + topicId + "&update=1");

//        UserDTO u = Client.create()
//                .resource("http://localhost:8080/api/user/" + 6)
//                .accept(MediaType.APPLICATION_JSON_TYPE)
//                .get(UserDTO.class);
//
//        TopicDTO t = Client.create()
//                .resource("http://localhost:8080/api/topic/" + topicId)
//                .accept(MediaType.APPLICATION_JSON_TYPE)
//                .get(TopicDTO.class);

        PostDTO rec = new PostDTO();
        rec.setTitle(title);
        rec.setContent(content);
        rec.setUser(6L);
        rec.setTopicId(topicId);
//        rec.setAuthor(u);
//        rec.setTopic(t);
        Client.create().resource("http://localhost:8080/api/post/").post(rec);
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

}