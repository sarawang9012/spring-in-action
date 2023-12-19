package com.sia.tacos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

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
public class Taco {
    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;
    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
