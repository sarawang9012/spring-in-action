package com.sia.tacos.model;

import lombok.Data;

/**
 * ClassName: IngredientRef
 * Package: com.sia.tacos.model
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/21 10:52
 * @Version 1.0
 */
public record IngredientRef(String ingredient, Long taco) {
}
