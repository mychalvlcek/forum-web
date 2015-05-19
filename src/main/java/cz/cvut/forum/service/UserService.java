package cz.cvut.forum.service;

import cz.cvut.forum.dto.UserDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> getAll();

    public UserDTO login(String username, String password);

    public UserDTO get(Long id);

    public void delete(Long id);

    public Long save(UserDTO dto);
}
