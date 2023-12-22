package com.sia.tacos.repository;

import org.springframework.stereotype.Repository;

/**
 * ClassName: IngredientRefRepository
 * Package: com.sia.tacos.repository
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/22 11:40
 * @Version 1.0
 */
@Repository
public interface IngredientRefRepository {
    Iterable<String> getIngredientId(Long tacoId);
}
