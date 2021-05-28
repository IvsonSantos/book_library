package com.lampiris.repository;

import com.lampiris.entity.BookFamily;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFamilyRepository extends JpaRepository<BookFamily, Integer> {

    BookFamily findByName(String name);

}
