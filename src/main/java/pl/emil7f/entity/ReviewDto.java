package pl.emil7f.entity;

public class ReviewDto {

    private final Long id;
    private final String content;
    private final int rating;

    public ReviewDto(Long id, String content, int rating) {
        this.id = id;
        this.content = content;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }
}
