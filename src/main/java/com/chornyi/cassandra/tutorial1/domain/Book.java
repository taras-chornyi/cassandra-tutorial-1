package com.chornyi.cassandra.tutorial1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Book {

    private UUID id;
    private String title;
    private String author;
    private String subject;
    private String publisher;

    public Book(UUID id, String title, String author, String subject) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.subject = subject;
    }

}
