package cz.cvut.forum.service;

import cz.cvut.forum.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAll();

    public List<PostDTO> getByTopic(Long id);

    public PostDTO get(Long id);

    public void delete(Long id);

    public Long save(PostDTO dto);
}
