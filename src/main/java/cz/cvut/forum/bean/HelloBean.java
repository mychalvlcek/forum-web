package cz.cvut.forum.bean;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import cz.cvut.forum.dto.CategoryDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryDTO> getCategories() {
        return Client.create().resource("http://localhost:8080/api/category/").get(new GenericType<List<CategoryDTO>>(){});
    }
}