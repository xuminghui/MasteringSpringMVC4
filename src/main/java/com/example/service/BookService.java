package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Book;
import com.example.repository.BookRepository;
@Service
public class BookService {
	private BookRepository bookRepository;
	@Autowired
	public BookService(BookRepository bookRepository){
		this.bookRepository=bookRepository;
	}
	
	public Iterable<Book> getAllBooks(){
		return bookRepository.findAll();
	}

	public Book findBookByIsbn(String isbn) {
		return bookRepository.findBookByIsbn(isbn);
	}
	
}
