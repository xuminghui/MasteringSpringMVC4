package com.example.starter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.Publisher;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.repository.PublisherRepository;

public class StartupRunnerForBooks implements CommandLineRunner {
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private BookRepository bookRepository;

	@Override
	public void run(String... arg0) throws Exception {
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 10; i++) {
			Author author = new Author("firstName" + i, "lastName" + i);
			Publisher publisher = new Publisher("publisherName" + i);
			Book book = new Book(i + "", "title1", author, publisher);
			books.add(book);
		}
		bookRepository.save(books);

		logger.info("Number	of	books:	" + bookRepository.count());

		Author author = new Author("Alex", "Antonov");
		Publisher publisher = new Publisher("Packt");
		Book book = new Book("978-1-78528-415-1", "Spring	Boot	Recipes", author, publisher);
		bookRepository.save(book);

	}

	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void run() {
		logger.info("Number	of	books:	" + bookRepository.count());
	}

}
