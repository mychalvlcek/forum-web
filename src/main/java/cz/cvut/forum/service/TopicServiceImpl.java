package cz.cvut.forum.service;

import com.sun.jersey.api.client.GenericType;
import cz.cvut.forum.dto.TopicDTO;
import java.util.List;

public class TopicServiceImpl extends AbstractDataAccessService implements TopicService {

    public static final String RESOURCE_URL = "api/topic/";

    @Override
    public List<TopicDTO> getAll() {
        return getClient().resource(API_URL + RESOURCE_URL).get(new GenericType<List<TopicDTO>>(){});
    }

    @Override
    public List<TopicDTO> getByCategory(Long id) {
        return getClient().resource(API_URL + RESOURCE_URL + "category/" + id).get(new GenericType<List<TopicDTO>>(){});
    }


    @Override
    public TopicDTO get(Long id) {
        return getClient().resource(API_URL + RESOURCE_URL + id).get(TopicDTO.class);
    }

    @Override
    public void delete(Long id) {
        getClient().resource(API_URL + RESOURCE_URL + id).delete();
    }


    @Override
    public Long save(TopicDTO dto) {
        getClient().resource(API_URL + RESOURCE_URL).post(dto);
        return 1L;
    }
}
