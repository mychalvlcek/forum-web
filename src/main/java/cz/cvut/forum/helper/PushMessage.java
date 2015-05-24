package cz.cvut.forum.helper;

public class PushMessage {

    private Long id;
    private Long category;
    private String title;

    public PushMessage() {
    }

    public PushMessage(Long id, String title, Long category) {
        this.title = title;
        this.id = id;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}