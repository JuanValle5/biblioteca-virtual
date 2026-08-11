package com.juanjo.biblioteca.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "book",
        indexes = {
                @Index(name = "idx_book_title", columnList = "title, author_id",unique = true)
        }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "El titulo es obligatorio")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(nullable = false)
    @NotNull
    private Integer pages;

    @Column(nullable = false)
    @NotNull
    private Boolean read;
}

