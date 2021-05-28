package com.lampiris.service;

import com.lampiris.dto.BookFamilyDTO;
import com.lampiris.entity.BookFamily;

import java.util.List;

public interface BookFamilyService {

    List<BookFamily> getAll();
    BookFamily save(BookFamily bookFamily);
    BookFamily fromDTO(BookFamilyDTO dto);
    BookFamily update(BookFamily bookFamily);
    void generateExcel();
}
