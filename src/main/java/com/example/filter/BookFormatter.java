package com.example.filter;

import java.util.Locale;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import com.example.entity.Book;
import com.example.repository.BookRepository;

public class BookFormatter implements Formatter<Book> {
	private BookRepository repository;

	public BookFormatter(BookRepository repository) {
		this.repository = repository;
	}

	@Override
	public Book parse(String bookIdentifier, Locale locale) throws ParseException {
		Book book = repository.findBookByIsbn(bookIdentifier);
		return book != null ? book : repository.findOne(Long.valueOf(bookIdentifier));
	}

	@Override
	public String print(Book book, Locale locale) {
		return book.getIsbn();
	}
}