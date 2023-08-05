package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner jpaTest(BookRepository bookRepository, AuthorRepository authorRepository) {
		return args -> {
			// Create Authors
			Author author1 = new Author();
			author1.setName("Author1");
			authorRepository.save(author1);

			Author author2 = new Author();
			author2.setName("Author2");
			authorRepository.save(author2);

			// Log authors

			System.out.println("\nAll Authors:");
			authorRepository.findAll().forEach(x -> System.out.println(x.getName()));

			// for (var author : authorRepository.findAll()) {
			// System.out.println(author.getName());
			// }

			// // Make some books
			Book book1 = new Book(); // create a new book
			book1.setTitle("Book1"); // set the title of the book
			Set<Author> book1Authors = new HashSet<>(); // create a Set to hold the authors
			book1Authors.add(author1); // add author to the above set
			book1.setAuthors(book1Authors); // assign the authors set to our book
			bookRepository.save(book1); // give the book to the repository and save it.

			Book book2 = new Book();
			book2.setTitle("Book2");
			Set<Author> book2Authors = new HashSet<>();
			book2Authors.add(author2);
			book2.setAuthors(book2Authors);
			bookRepository.save(book2);

			Book book3 = new Book();
			book3.setTitle("Book3");
			Set<Author> book3Authors = new HashSet<>();
			book3Authors.add(author1);
			book3Authors.add(author2);
			book3.setAuthors(book3Authors);
			bookRepository.save(book3);

			System.out.println("\nAll Books");
			// bookRepository.findAll().forEach(System.out::println);
			bookRepository.findAll().forEach(author ->
			System.out.println(author.getTitle()));
			
			var books = bookRepository.findByAuthors_Name("Author2");
			books.forEach(x -> System.out.println(x.getTitle()));

			// books.forEach(book => console.log(book.title))

		};
	}
}
