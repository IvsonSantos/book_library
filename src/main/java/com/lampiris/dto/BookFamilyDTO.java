package com.lampiris.dto;

import com.lampiris.entity.BookFamily;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class BookFamilyDTO {

    private Integer id;

    @NotEmpty(message = "Name is required")
    private String name;

    public BookFamilyDTO(BookFamily bookFamily) {
        id = bookFamily.getId();
        name = bookFamily.getName();
    }

}
