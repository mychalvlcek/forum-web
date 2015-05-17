package cz.cvut.forum.service;

import cz.cvut.forum.dto.TopicDTO;

import java.util.List;

public interface TopicService {

    public List<TopicDTO> getAll();

    public List<TopicDTO> getByCategory(Long id);

    public TopicDTO get(Long id);

    public void delete(Long id);

    public Long save(TopicDTO dto);
}
