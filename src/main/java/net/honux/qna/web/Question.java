package net.honux.qna.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;
    private String author;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String question;

    public Question() {}

    public Question(String author, String title, String question) {
        this.author = author;
        this.title = title;
        this.question = question;
    }
}
