package com.sia.tacos.utility;

import com.sia.tacos.model.Ingredient;
import com.sia.tacos.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * ClassName: IngredientByIdConverter
 * Package: com.sia.tacos.web
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/15 16:51
 * @Version 1.0
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;
    public IngredientByIdConverter(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }

}
