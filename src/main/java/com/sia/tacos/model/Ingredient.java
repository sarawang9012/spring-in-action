package com.sia.tacos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: Ingredient
 * Package: com.sia.tacos
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/15 11:31
 * @Version 1.0
 */

@Data
@Entity
public class Ingredient {

    @Id
    @Column(name = "ingredient_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
