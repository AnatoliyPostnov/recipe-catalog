package com.postnov.recipecatalog.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @Column(name = "name", updatable = false)
    private String name;

    private String extension;
}
