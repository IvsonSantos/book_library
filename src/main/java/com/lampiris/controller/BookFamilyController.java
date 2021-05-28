package com.lampiris.controller;

import com.lampiris.dto.BookFamilyDTO;
import com.lampiris.entity.BookFamily;
import com.lampiris.service.BookFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/bookFamilies")
public class BookFamilyController {

    @Autowired
    private BookFamilyService service;

    @GetMapping
    public ResponseEntity<List<BookFamilyDTO>> getAll() {
        List<BookFamily> list = service.getAll();
        List<BookFamilyDTO> listDTO = list.stream().map(
            bookFamily -> new BookFamilyDTO(bookFamily)).collect(Collectors.toList()
        );
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody BookFamilyDTO dto) {

        BookFamily bookFamily = service.fromDTO(dto);
        bookFamily = service.save(bookFamily);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(bookFamily.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody BookFamilyDTO dto, @PathVariable Integer id) {
        BookFamily bookFamily = service.fromDTO(dto);
        bookFamily.setId(id);
        bookFamily = service.update(bookFamily);
        return ResponseEntity.noContent().build();
    }

}
