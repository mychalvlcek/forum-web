package cz.cvut.forum.bean;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import cz.cvut.forum.dto.CategoryDTO;
import cz.cvut.forum.dto.TopicDTO;
import cz.cvut.forum.helper.FacesUtil;
import org.primefaces.context.RequestContext;
//import cz.cvut.wpa.forum.helper.FacesUtil;
//import cz.cvut.wpa.forum.service.CategoryService;
//import cz.cvut.wpa.forum.service.TopicService;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

@ManagedBean
@ViewScoped
public class NewTopic {

    private Long categoryId;
    private CategoryDTO category;

    private String title;

//    @Autowired
//    protected LoggedUser user;
//    @Autowired
//    protected TopicService topicService;
//    @Autowired
//    protected CategoryService categoryService;

    public void init() throws Exception {
        ClientResponse r = Client.create()
                .resource("http://localhost:8080/api/category/" + categoryId)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        System.out.println(r.getStatus());
        category = r.getEntity(CategoryDTO.class);
    }

    public void storeTopic() throws IOException {
//        Long newTopicId = topicService.addTopic(title, user.id(), categoryId);
//        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Topic", "Byl úspěšně vložen."));
//        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + newTopicId + "&new=1");

//        RequestContext.getCurrentInstance().update("growl");
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(severity, header, message));

        TopicDTO rec = new TopicDTO();
        rec.setTitle(title);
        rec.setAuthor(6L);
        rec.setCategory(categoryId);
        Client.create().resource("http://localhost:8080/api/topic/").post(rec);

        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Topic", "Byl úspěšně vložen."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/category.xhtml?id="+categoryId);
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

}