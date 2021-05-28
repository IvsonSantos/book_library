package com.lampiris.service;

import com.lampiris.dto.BookDTO;
import com.lampiris.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();
    Book save(Book bookFamily);
    Book fromDTO(BookDTO dto);
    Book update(Book bookFamily);

}
