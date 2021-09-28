package com.postnov.recipecatalog.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "extension", nullable = false)
    private String extension;
}
