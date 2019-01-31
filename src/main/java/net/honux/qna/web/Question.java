package net.honux.qna.web;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_author"))
    private User author;

    private String title;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(columnDefinition = "TEXT")
    private String contents;

    public Question() {}

    public Question(User author, String title, String question) {
        this.author = author;
        this.title = title;
        this.contents = question;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getContentsBr() {
        if (contents == null) {
            return "내용이 없습니다";
        }
        return contents.replace("\r\n", "<br>\n");
    }

    public String getFormattedDate() {
        if (dateCreated == null) {
            return "";
        }
        return dateCreated.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

    public boolean isUserAuthor(User user) {
        return user.equals(author);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", dateCreated=" + dateCreated +
                ", contents='" + contents + '\'' +
                '}';
    }
}
