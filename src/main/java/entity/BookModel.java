package entity;

import java.util.Objects;

public class BookModel {
    private Integer id;
    private String title;
    private int pagesCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookModel bookModel = (BookModel) o;
        return pagesCount == bookModel.pagesCount && Objects.equals(id, bookModel.id) && Objects.equals(title, bookModel.title);
    }


    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pagesCount=" + pagesCount +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, pagesCount);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }
}
