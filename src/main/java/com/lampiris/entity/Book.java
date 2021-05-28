package com.lampiris.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_family_id")
    private BookFamily bookFamily;

}
