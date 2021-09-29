package com.postnov.recipecatalog.model;

import com.postnov.recipecatalog.enums.Complexity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Picture.class, orphanRemoval = true)
    @JoinColumn(name = "picture_name", referencedColumnName = "name", nullable = false)
    private Picture picture;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Cuisine.class)
    @JoinTable(name = "cuisine_recipe",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "name", referencedColumnName = "name"))
    private List<Cuisine> recipe;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = StepCooking.class, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<StepCooking> stepsCooking;
}
