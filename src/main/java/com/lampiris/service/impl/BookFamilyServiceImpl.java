package com.lampiris.service.impl;

import com.lampiris.dto.BookFamilyDTO;
import com.lampiris.entity.BookFamily;
import com.lampiris.repository.BookFamilyRepository;
import com.lampiris.service.BookFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookFamilyServiceImpl implements BookFamilyService {

    @Autowired
    private BookFamilyRepository repository;

    @Override
    public List<BookFamily> getAll() {
        List<BookFamily> bookFamilies = repository.findAll();
        return bookFamilies;
    }

    @Override
    public BookFamily save(BookFamily bookFamily) {
        return repository.save(bookFamily);
    }

    public BookFamily fromDTO(BookFamilyDTO dto) {
        return BookFamily.builder().id(dto.getId()).name(dto.getName()).build();
    }

    public BookFamily find(Integer id) {
        Optional<BookFamily> categoria = repository.findById(id);
        return categoria.orElseThrow(() ->
                new RuntimeException("Object not found: " + BookFamily.class.getName()));
    }

    @Override
    public BookFamily update(BookFamily bookFamily) {
        BookFamily newBookFamily = find(bookFamily.getId());
        newBookFamily.setName(bookFamily.getName());
        return repository.save(newBookFamily);
    }

}
