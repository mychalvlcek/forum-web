package cz.cvut.forum.bean;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import cz.cvut.forum.dto.CategoryDTO;
import cz.cvut.forum.service.CategoryService;
import cz.cvut.forum.service.CategoryServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private CategoryService service;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        this.service = new CategoryServiceImpl();
    }

    public List<CategoryDTO> getCategories() {
        return service.getAll();
    }
}