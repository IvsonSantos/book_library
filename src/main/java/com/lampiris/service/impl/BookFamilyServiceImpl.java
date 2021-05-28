package com.lampiris.service.impl;

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
    public List<BookFamily> getAllBookFamilies() {
        List<BookFamily> bookFamilies = bookFamilyRepository.findAll();
        return bookFamilies;
    }

}
