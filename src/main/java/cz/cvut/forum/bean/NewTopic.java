package cz.cvut.forum.bean;

import cz.cvut.forum.dto.CategoryDTO;
import cz.cvut.forum.dto.TopicDTO;
import cz.cvut.forum.helper.FacesUtil;
import cz.cvut.forum.service.CategoryService;
import cz.cvut.forum.service.CategoryServiceImpl;
import cz.cvut.forum.service.TopicService;
import cz.cvut.forum.service.TopicServiceImpl;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class NewTopic {

    private Long categoryId;
    private CategoryDTO category;

    private String title;

    private CategoryService categoryService;
    private TopicService topicService;

    @PostConstruct
    public void postConstruct() {
        this.categoryService = new CategoryServiceImpl();
        this.topicService = new TopicServiceImpl();
    }

    public void init() throws Exception {
        category = categoryService.get(categoryId);
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
        topicService.save(rec);

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