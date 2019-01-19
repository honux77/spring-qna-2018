package net.honux.qna.web;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;

    @CreationTimestamp
    private LocalDate dateCreated;

    @Column(columnDefinition = "TEXT")
    private String question;

    public Question() {}

    public Question(String author, String title, String question) {
        this.author = author;
        this.title = title;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
}
