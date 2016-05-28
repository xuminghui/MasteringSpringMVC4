package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Book;
import com.example.entity.Isbn;
import com.example.entity.Reviewer;
import com.example.repository.BookRepository;
import com.example.service.BookService;

@RestController
@RequestMapping("/books")
public class BookRestController {

	/*
	 * @Autowired private JdbcTemplate jdbcTemplate;
	 */
	
	private BookService bookService;
	@Autowired
	public BookRestController(BookService bookService){
		this.bookService=bookService;
	}
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	/**
	 * 直接返回json格式
	 * 
	 * @param isbn
	 * @return
	 */
	@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
	public Book getBook(@PathVariable Isbn isbn, Model model) {
		return bookService.findBookByIsbn(isbn.getIsbn());
	}

	@RequestMapping(value = "/{isbn}/reviewers", method = RequestMethod.GET)
	public List<Reviewer> getReviewers(@PathVariable("isbn") Book book) {
		return book.getReviewers();
	}

	
}