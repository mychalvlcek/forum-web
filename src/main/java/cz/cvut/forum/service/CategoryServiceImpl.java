package cz.cvut.forum.service;

import com.sun.jersey.api.client.GenericType;
import cz.cvut.forum.dto.CategoryDTO;
import java.util.List;

public class CategoryServiceImpl extends AbstractDataAccessService implements CategoryService {

    public static final String RESOURCE_URL = "api/category/";

    @Override
    public List<CategoryDTO> getAll() {
        return getClient().resource(API_URL + RESOURCE_URL).get(new GenericType<List<CategoryDTO>>(){});
    }


    @Override
    public CategoryDTO get(Long id) {
        return getClient().resource(API_URL + RESOURCE_URL + id).get(CategoryDTO.class);
    }

    @Override
    public void delete(Long id) {
        getClient().resource(API_URL + RESOURCE_URL + id).delete();
    }


    @Override
    public Long save(CategoryDTO dto) {
//        getClient().resource("http://localhost:8080/api/topic/").post(dto);
        getClient().resource(API_URL + RESOURCE_URL).post(dto);
        return 1L;
    }
}
