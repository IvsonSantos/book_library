package com.lampiris.service.impl;

import com.lampiris.dto.BookDTO;
import com.lampiris.entity.Book;
import com.lampiris.entity.BookFamily;
import com.lampiris.repository.BookFamilyRepository;
import com.lampiris.repository.BookRepository;
import com.lampiris.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookFamilyRepository bookFamilyRepository;

    @Override
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book fromDTO(BookDTO dto) {
        BookFamily bookFamily = bookFamilyRepository.findByName(dto.getFamily());
        return Book.builder().title(dto.getTitle()).author(dto.getAuthor()).bookFamily(bookFamily).build();
    }

    public Book find(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() ->
                new RuntimeException("Object not found: " + Book.class.getName()));
    }

    @Override
    public Book update(Book book) {
        Book newBook = find(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setBookFamily(book.getBookFamily());
        return bookRepository.save(newBook);
    }

}
