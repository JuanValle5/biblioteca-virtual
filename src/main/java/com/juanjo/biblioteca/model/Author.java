package com.juanjo.biblioteca.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "author"
)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre debe ser obligatorio")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "La nacionalidad debe ser obligatoria")
    private String nationality;

    @OneToMany(mappedBy = "author")
    @Builder.Default
    private List<Book> books = new ArrayList<>();

}
