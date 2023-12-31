package com.sia.tacos.controller;

import com.sia.tacos.model.Ingredient;
import com.sia.tacos.model.Taco;
import com.sia.tacos.model.TacoOrder;
import com.sia.tacos.repository.IngredientRepository;
import com.sia.tacos.repository.TacoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: DesignTacoController
 * Package: com.sia.tacos
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/15 11:46
 * @Version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {

        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();
        System.out.println(ingredients);
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));

        }


    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    /**
     * The @Valid annotation tells Spring MVC to perform validation on the submitted Taco
     * object after it’s bound to the submitted form data and before the processTaco()
     * method is called. If there are any validation errors, the details of those errors
     * will be captured in an Errors object that’s passed into processTaco().
     *
     * @param taco
     * @param errors
     * @param tacoOrder
     * @return
     */
    @PostMapping
    public String processTaco(@Valid Taco taco,
                              Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        taco.setTacoOrder(tacoOrder);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients,
                                              Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
