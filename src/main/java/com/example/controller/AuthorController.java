package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Author;
import com.example.repository.AuthorRepository;

//@RestController
//@RequestMapping("/authors")
public class AuthorController {


	@Autowired
	private AuthorRepository authorRepository;

	//@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<Author> getAllAuthors() {
		return authorRepository.findAll();
	}


}