package cz.cvut.forum.dto;

import java.util.Date;
import java.util.List;

public class CategoryDTO extends AbstractDTO {
    private String title;
    private List<Long> topics;

    public CategoryDTO() {

    }

    public CategoryDTO(Long id, String title, List<Long> topics, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.topics = topics;
//        this.created = created;
//        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getTopics() {
        return topics;
    }

    public void setTopics(List<Long> topics) {
        this.topics = topics;
    }

    public int getTopicsCount() {
        return this.topics.size();
    }

}