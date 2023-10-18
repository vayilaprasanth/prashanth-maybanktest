package com.assessment.okl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.okl.domain.Book;
import com.assessment.okl.service.BookService;

/**
 * Rest controller call which holds the information about the REST api's exposed and Trigger will come here when the api get's the request
 * 
 *
 */
@RestController
@RequestMapping("/api")
public class BookResource {
	
	private static final Logger log = LoggerFactory.getLogger(BookResource.class);
	
	@Autowired
	private BookService bookService;
	
	/**
	 * API to create a book in the data base
	 * 
	 * @param book
	 * @return
	 */
	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		log.info("Request to create a book :: {}", book);
		Book bookResponse = bookService.createBook(book);
		log.info("Response to create a book :: {}", bookResponse);
		return new ResponseEntity<Book>(bookResponse, HttpStatus.CREATED);
	}
	
	/**
	 * API to update a book details
	 * 
	 * @param book
	 * @return
	 */
	@PutMapping("/book")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		log.info("Request to update a book by id : {}", book);
		if(book.getId() == null || bookService.getBook(book.getId()) == null) {
			return new ResponseEntity<Book>(book, HttpStatus.BAD_REQUEST);
		}
		Book updatedBook = bookService.updateBook(book);
		log.info("Response to update a book by Id : {}", updatedBook);
		return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
	}

	/**
	 * API to get one book details by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Long id) {
		log.info("Request to get a book by id : {}", id);
		Book book = bookService.getBook(id);
		log.info("Response to get a book by Id : {}", book);
		return book != null ? new ResponseEntity<Book>(book, HttpStatus.OK): new ResponseEntity<Book>(book, HttpStatus.NO_CONTENT);
	}
	
	/**
	 * API to get list of all the books in the db with pagination
	 * 
	 * @return
	 */
	@GetMapping("/books")
	public ResponseEntity<Page<Book>> getAllBooks(@RequestParam("page") int page) {
		log.info("Request to get all the books");
		Pageable pageable = PageRequest.of(page, 10);
		Page<Book> bookList = bookService.getAllBooks(pageable);
		log.info("Response to get all the books : {}", bookList);
		return new ResponseEntity<Page<Book>>(bookList, HttpStatus.OK);
	}
	

	/**
	 * API to delete the book with id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		log.info("Request to delete a book by id : {}", id);
		if(bookService.getBook(id) != null) {
			bookService.deleteBook(id);
		}
		log.info("Response to delete book by Id : {}", id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * API to get call third party service and get the data
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("third-party-name-suggestion/{id}")
	public ResponseEntity<String> getBookName(@PathVariable Long id) {
		log.info("Request to call third party name suggestions with id: {}", id);
		Book book = bookService.getDataFromThirdPartyAPI(id);
		log.info("Response to call third party name suggestions with id : {}", book);
		return book != null ? new ResponseEntity<String>(book.getName(), HttpStatus.OK): new ResponseEntity<String>("No suggestion available", HttpStatus.OK);
	}
}
