package cz.cvut.forum.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import cz.cvut.forum.dto.CategoryDTO;
import cz.cvut.forum.dto.TopicDTO;
import cz.cvut.forum.service.CategoryService;
import cz.cvut.forum.service.CategoryServiceImpl;
import cz.cvut.forum.service.TopicService;
import cz.cvut.forum.service.TopicServiceImpl;

@ManagedBean
@ViewScoped
public class CategoryBean {
    private Long id;
    private CategoryDTO category;

    private CategoryService service;
    private TopicService topicService;

    @PostConstruct
    public void postConstruct() {
        this.service = new CategoryServiceImpl();
        this.topicService = new TopicServiceImpl();
    }

    public void init() {
        category = service.get(id);
    }

    public List<TopicDTO> getTopics() {
        return topicService.getByCategory(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

}
