package com.assessment.okl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.assessment.okl.domain.Book;

/**
 * BookService service layer interface to define the service contracts related to Book object 
 * 
 *
 */

public interface BookService {
	
	public Book createBook(Book book);

	public Book updateBook(Book book);

	public Book getBook(Long id);

	public Page<Book> getAllBooks(Pageable page);
	
	public void deleteBook(Long id);

	public Book getDataFromThirdPartyAPI(Long id);
	
}
