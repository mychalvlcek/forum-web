package cz.cvut.forum.service;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import cz.cvut.forum.dto.UserDTO;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class UserServiceImpl extends AbstractDataAccessService implements UserService {

    public static final String RESOURCE_URL = "api/user/";

    @Override
    public List<UserDTO> getAll() {
        return getClient().resource(API_URL + RESOURCE_URL).get(new GenericType<List<UserDTO>>(){});
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO user = null;
        ClientResponse response = getClient().resource(API_URL + RESOURCE_URL + "login")
                                .queryParam("email", email)
                                .queryParam("password", password)
                                .get(ClientResponse.class);
        if (response.getStatus() == 200) {
            user = response.getEntity(UserDTO.class);
        }
        return user;
    }

    @Override
    public UserDTO get(Long id) {
        return getClient().resource(API_URL + RESOURCE_URL + id).get(UserDTO.class);
    }

    @Override
    public void delete(Long id) {
        getClient().resource(API_URL + RESOURCE_URL + id).delete();
    }


    @Override
    public Long save(UserDTO dto) {
        getClient().resource(API_URL + RESOURCE_URL).type(MediaType.APPLICATION_JSON_TYPE).post(dto);
        return 1L;
    }
}
