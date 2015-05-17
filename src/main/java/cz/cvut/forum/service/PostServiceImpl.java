package cz.cvut.forum.service;

import com.sun.jersey.api.client.GenericType;
import cz.cvut.forum.dto.PostDTO;
import java.util.List;

public class PostServiceImpl extends AbstractDataAccessService implements PostService {

    public static final String RESOURCE_URL = "api/post/";

    @Override
    public List<PostDTO> getAll() {
        return getClient().resource(API_URL + RESOURCE_URL).get(new GenericType<List<PostDTO>>(){});
    }

    @Override
    public List<PostDTO> getByTopic(Long id) {
        return getClient().resource(API_URL + RESOURCE_URL + "topic/" + id).get(new GenericType<List<PostDTO>>(){});
    }


    @Override
    public PostDTO get(Long id) {
        return getClient().resource(API_URL + RESOURCE_URL + id).get(PostDTO.class);
    }

    @Override
    public void delete(Long id) {
        getClient().resource(API_URL + RESOURCE_URL + id).delete();
    }


    @Override
    public Long save(PostDTO dto) {
        getClient().resource(API_URL + RESOURCE_URL).post(dto);
        return 1L;
    }
}
