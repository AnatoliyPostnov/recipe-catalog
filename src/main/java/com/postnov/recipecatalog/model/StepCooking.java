package com.postnov.recipecatalog.model;

import javax.persistence.*;

@Entity
@Table(name = "step_cooking")
public class StepCooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Recipe.class)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
