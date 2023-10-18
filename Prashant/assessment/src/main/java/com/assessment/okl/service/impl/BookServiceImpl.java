package com.assessment.okl.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.okl.domain.Book;
import com.assessment.okl.repository.BookRepository;
import com.assessment.okl.service.BookService;

/**
 * This implementation layer class will implement the BookService's contacts
 * 
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	/**
	 * Method to create a book inside db
	 * 
	 */
	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	/**
	 * Method to update an existing book inside db
	 */
	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	/**
	 * Method to get a book from the database based on id
	 */
	@Override
	public Book getBook(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		return book.isPresent() ? book.get() : null;
	}

	/**
	 * Method to get all the books available in the database with pagination
	 */
	@Override
	public Page<Book> getAllBooks(Pageable page) {
		return bookRepository.findAll(page);
	}

	/**
	 * Method to delete a book in the database
	 */
	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

	/**
	 * Method to call the third party public api to get the data
	 */
	@Override
	public Book getDataFromThirdPartyAPI(Long id) {
		String uri = "https://api.restful-api.dev/objects/" + id;
        RestTemplate restTemplate = new RestTemplate(); // we can use webclient or resttemplate both to call a third party service 
        Book response = restTemplate.getForObject(uri, Book.class); // getForObject is a method to call for a GET api
		return response;
	}

	
}
