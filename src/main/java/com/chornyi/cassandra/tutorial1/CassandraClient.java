package com.chornyi.cassandra.tutorial1;

import com.chornyi.cassandra.tutorial1.domain.Book;
import com.chornyi.cassandra.tutorial1.repository.BookRepository;
import com.chornyi.cassandra.tutorial1.repository.KeyspaceRepository;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CassandraClient {

    public static void main(String[] args) {

        CassandraConnector connector = new CassandraConnector();
        connector.connect("127.0.0.1", null);
        Session session = connector.getSession();

        KeyspaceRepository sr = new KeyspaceRepository(session);
        sr.createKeyspace("library", "SimpleStrategy", 1);
        sr.useKeyspace("library");

        BookRepository br = new BookRepository(session);
        br.createTable();
        br.alterTablebooks("publisher", "text");

        br.createTableBooksByTitle();

        Book book = new Book(UUIDs.timeBased(), "Effective Java", "Joshua Bloch", "Programming");
        br.insertBookBatch(book);

        br.selectAll().forEach(o -> log.info("Title in books: " + o.getTitle()));
        br.selectAllBookByTitle().forEach(o -> log.info("Title in booksByTitle: " + o.getTitle()));

        br.deletebookByTitle("Effective Java");
        br.deleteTable("books");
        br.deleteTable("booksByTitle");

        sr.deleteKeyspace("library");

        connector.close();
        
    }

}
