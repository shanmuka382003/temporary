// BookRepository.java
package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    // Example method to display book details. This method would interact with database.
    public void displayBooks() {
        System.out.println("Displaying books from the repository...");
    }
}
