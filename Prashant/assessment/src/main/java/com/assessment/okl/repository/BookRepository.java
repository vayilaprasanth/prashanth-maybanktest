package com.assessment.okl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.okl.domain.Book;

/**
 * 
 * JPA Repository class to communicate with the database
 * 
 * This class helps to persist and retrieves data from the database for the respective entity (@Book)
 *
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	/**
	 * Gets the elements based on page
	 */
	Page<Book> findAll(Pageable page);
}
