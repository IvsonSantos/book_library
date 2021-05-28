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
    private BookFamilyService bookFamilyService;

    @GetMapping
    public ResponseEntity<List<BookFamilyDTO>> getAll() {
        List<BookFamily> list = bookFamilyService.getAll();
        List<BookFamilyDTO> listDTO = list.stream().map(
            bookFamily -> new BookFamilyDTO(bookFamily)).collect(Collectors.toList()
        );
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody BookFamilyDTO dto) {

        BookFamily bookFamily = bookFamilyService.fromDTO(dto);
        bookFamily = bookFamilyService.save(bookFamily);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(bookFamily.getId())	// o codigo dacima
                .toUri();	// converte para URI

        return ResponseEntity.created(uri).build();
    }


}
