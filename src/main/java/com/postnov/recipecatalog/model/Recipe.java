package com.postnov.recipecatalog.model;

import com.postnov.recipecatalog.enums.Complexity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "time_cooking", nullable = false)
    private LocalDateTime timeCooking;

    @Column(name = "calories", nullable = false)
    private String calories;

    @Enumerated(EnumType.STRING)
    @Column(name = "complexity", nullable = false)
    private Complexity complexity;

    @Column(name = "cuisine", nullable = false)
    private String cuisine;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "picture_name", referencedColumnName = "name", nullable = false)
    private Picture picture;

}
