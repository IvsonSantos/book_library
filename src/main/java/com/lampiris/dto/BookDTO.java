package com.lampiris.dto;

import com.lampiris.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class BookDTO {

    private Integer id;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Author is required")
    private String author;

    @NotEmpty(message = "Book Family is required")
    private String family;

    public BookDTO(Book book) {
        id = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        family = book.getBookFamily().getName();
    }

}
