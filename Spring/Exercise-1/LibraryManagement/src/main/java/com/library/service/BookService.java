package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Method to delegate book display functionality to the repository
    public void displayBooks() {
        System.out.println("Service: Delegating to book repository...");
        bookRepository.displayBooks(); // Here the call to display books is done..
    }
}
