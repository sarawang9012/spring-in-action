package com.sia.tacos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName: Taco
 * Package: com.sia.tacos
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/15 11:40
 * @Version 1.0
 */
@Data
@Entity
@ToString(exclude = "tacoOrder")
public class Taco {
    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    @ManyToMany()
    @JoinTable(name = "ingredient_taco", joinColumns = @JoinColumn(name = "taco_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "order_id")
    private TacoOrder tacoOrder;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taco_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }


}
