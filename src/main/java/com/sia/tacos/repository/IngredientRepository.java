package com.sia.tacos.repository;

import com.sia.tacos.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ClassName: IngredientRepository
 * Package: com.sia.tacos.utility
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/20 11:19
 * @Version 1.0
 */

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);

}
