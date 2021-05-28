package com.lampiris.service.impl;

import com.lampiris.dto.BookFamilyDTO;
import com.lampiris.entity.BookFamily;
import com.lampiris.repository.BookFamilyRepository;
import com.lampiris.service.BookFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookFamilyServiceImpl implements BookFamilyService {

    @Autowired
    private BookFamilyRepository bookFamilyRepository;

    @Override
    public List<BookFamily> getAll() {
        List<BookFamily> bookFamilies = bookFamilyRepository.findAll();
        return bookFamilies;
    }

    @Override
    public BookFamily save(BookFamily bookFamily) {
        return bookFamilyRepository.save(bookFamily);
    }

    public BookFamily fromDTO(BookFamilyDTO dto) {
        return BookFamily.builder().id(dto.getId()).name(dto.getName()).build();
    }


}
