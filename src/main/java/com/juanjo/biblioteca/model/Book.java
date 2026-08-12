package com.juanjo.biblioteca.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "book",
        indexes = {
                @Index(name = "idx_book_title", columnList = "title, author_id, isbn",unique = true)
        }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "El titulo es obligatorio")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "El isbn es obligatorio")
    private String isbn;

    @Column(nullable = false)
    @NotBlank
    private String description;

    @Column(name = "publication_date", nullable = false)
    @NotNull
    private LocalDate publicationDate;

    @Column(nullable = false)
    @NotNull
    private Integer pages;

    @Column(nullable = false)
    @NotBlank
    private String language;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String publisher;

    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(nullable = false)
    @NotNull
    private Boolean read;

}

