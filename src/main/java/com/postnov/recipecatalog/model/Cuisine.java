package com.postnov.recipecatalog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "cuisine")
public class Cuisine {

    @Id
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Recipe.class)
    @JoinTable(name = "cuisine_recipe",
            joinColumns = @JoinColumn(name = "name", referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private List<Recipe> recipe;
}
