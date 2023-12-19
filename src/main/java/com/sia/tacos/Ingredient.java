package com.sia.tacos;
import lombok.Data;

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
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
