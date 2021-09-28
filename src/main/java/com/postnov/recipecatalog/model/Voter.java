package com.postnov.recipecatalog.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(Voter.VoterPK.class)
public class Voter {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Recipe.class)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = RegistrationUser.class)
    @JoinColumn(name = "user_login", referencedColumnName = "login")
    private RegistrationUser registrationUser;

    @Column(name = "evaluation", nullable = false)
    private Double evaluation;

    @Data
    public static class VoterPK implements Serializable {
        private Recipe recipe;
        private RegistrationUser registrationUser;
    }
}