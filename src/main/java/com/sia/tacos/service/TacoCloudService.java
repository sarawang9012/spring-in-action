package com.sia.tacos.service;

import com.sia.tacos.model.Ingredient;
import com.sia.tacos.model.Taco;
import com.sia.tacos.model.TacoOrder;
import com.sia.tacos.repository.IngredientRefRepository;
import com.sia.tacos.repository.IngredientRepository;
import com.sia.tacos.repository.OrderRepository;
import com.sia.tacos.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TacoCloudService
 * Package: com.sia.tacos.service
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/22 12:01
 * @Version 1.0
 */
@Service
public class TacoCloudService {
    private final IngredientRepository ingredientRepository;
    private final IngredientRefRepository ingredientRefRepository;
    private final TacoRepository tacoRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public TacoCloudService(IngredientRepository ingredientRepository,
                            IngredientRefRepository ingredientRefRepository,
                            TacoRepository tacoRepository, OrderRepository orderRepository){
        this.ingredientRepository = ingredientRepository;
        this.ingredientRefRepository = ingredientRefRepository;
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
    }

    public Iterable<TacoOrder> findAllOrders(){
        Iterable<TacoOrder> orders = orderRepository.getAll();
        for(TacoOrder order : orders){
            Iterable<Taco> tacos = tacoRepository.getByOrderId(order.getId());
            for(Taco taco: tacos){
                order.addTaco(taco);
                //find related ingredients for taco
                Iterable<String> ingredientIds =
                        ingredientRefRepository.getIngredientId(taco.getId());
                List<Ingredient> ingredients = new ArrayList<>();
                for(String ingredientId : ingredientIds){
                    Ingredient ingredient =
                            ingredientRepository.findById(ingredientId).orElse(null);
                    ingredients.add(ingredient);

                }
                taco.setIngredients(ingredients);
            }
        }
       return orders;
    }

}
