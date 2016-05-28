package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	public Book findBookByIsbn(String isbn);
	
}