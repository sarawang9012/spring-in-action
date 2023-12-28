package com.sia.tacos.service;

import com.sia.tacos.model.Ingredient;
import com.sia.tacos.model.Taco;
import com.sia.tacos.model.TacoOrder;
import com.sia.tacos.repository.IngredientRepository;
import com.sia.tacos.repository.OrderRepository;
import com.sia.tacos.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private final TacoRepository tacoRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public TacoCloudService(IngredientRepository ingredientRepository,
                            TacoRepository tacoRepository, OrderRepository orderRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
    }

    public Iterable<TacoOrder> findAllOrders(){
        Iterable<TacoOrder> orders = orderRepository.findAll();
        System.out.println(orders);
       return orders;
    }


}
