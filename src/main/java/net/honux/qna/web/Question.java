package net.honux.qna.web;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String question;

    public Question() {}

    public Question(User author, String title, String question) {
        this.author = author;
        this.title = title;
        this.question = question;
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

    public String getText() {
        return question;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public String getFormattedDate() {
        if (dateCreated == null) {
            return "";
        }
        return dateCreated.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}
