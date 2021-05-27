package com.lampiris;

import com.lampiris.entity.Book;
import com.lampiris.entity.BookFamily;
import com.lampiris.repository.BookFamilyRepository;
import com.lampiris.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookFamilyRepository bookFamilyRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {

		BookFamily bookFamily1 = BookFamily.builder().name("Comic").build();
		BookFamily bookFamily2 = BookFamily.builder().name("Mystery").build();
		BookFamily bookFamily3 = BookFamily.builder().name("Adventure").build();
		BookFamily bookFamily4 = BookFamily.builder().name("Classic").build();
		BookFamily bookFamily5 = BookFamily.builder().name("Fantasy").build();
		bookFamilyRepository.saveAll(
			Arrays.asList(bookFamily1, bookFamily2, bookFamily3, bookFamily4, bookFamily5)
		);

		Book book1 = Book.builder().title("Life of Pi").author("Yann Martel").bookFamily(bookFamily3).build();
		Book book2 = Book.builder().title("Little Woman").author("Louisa Kay").bookFamily(bookFamily4).build();
		Book book3 = Book.builder().title("Watchmen").author("Louisa Kay").bookFamily(bookFamily1).build();
		Book book4 = Book.builder().title("The Adventures of Sherlock Holmes").author("Conan Doyle").bookFamily(bookFamily2).build();
		Book book5 = Book.builder().title("Circe").author("Madeline Miller").bookFamily(bookFamily5).build();
		Book book6 = Book.builder().title("The Water Dancer").author("Ta-Nehisi Coates").bookFamily(bookFamily5).build();
		bookRepository.saveAll(
			Arrays.asList(book1, book2, book3, book4, book5, book6)
		);

	}
}
