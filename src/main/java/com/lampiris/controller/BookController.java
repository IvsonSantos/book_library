package com.lampiris.controller;

import com.lampiris.dto.BookDTO;
import com.lampiris.entity.Book;
import com.lampiris.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll() {
        List<Book> list = service.getAll();
        List<BookDTO> listDTO = list.stream().map(
                book -> new BookDTO(book)).collect(Collectors.toList()
        );
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody BookDTO dto) {
        Book book = service.fromDTO(dto);
        book = service.save(book);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody BookDTO dto, @PathVariable Integer id) {
        Book book = service.fromDTO(dto);
        book.setId(id);
        service.update(book);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report")
    public ResponseEntity<Void> report() {
        service.generateExcel();
        return ResponseEntity.noContent().build();
    }

}
