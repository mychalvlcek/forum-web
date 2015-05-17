package cz.cvut.forum.service;

import cz.cvut.forum.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public List<CategoryDTO> getAll();

    public CategoryDTO get(Long id);

    public void delete(Long id);

    public Long save(CategoryDTO dto);
}
