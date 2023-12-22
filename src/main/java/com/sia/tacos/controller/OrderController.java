package com.sia.tacos.controller;

import com.sia.tacos.model.Taco;
import com.sia.tacos.model.TacoOrder;
import com.sia.tacos.repository.IngredientRepository;
import com.sia.tacos.repository.OrderRepository;
import com.sia.tacos.repository.TacoRepository;
import com.sia.tacos.service.TacoCloudService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * ClassName: OrderController
 * Package: com.sia.tacos
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/15 17:00
 * @Version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;
    private final TacoRepository tacoRepository;

    private final TacoCloudService service;
    @Autowired
    public OrderController(OrderRepository orderRepository, TacoRepository tacoRepository,
                           TacoCloudService service){
        this.orderRepository = orderRepository;
        this.tacoRepository = tacoRepository;
        this.service = service;
    }
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    /**
     * @param order,         TacoOrder object whose properties are bound to the submitted
     *                       form fields
     * @param sessionStatus, The TacoOrder object was initially created and placed into the
     *                     session when the user created their first taco. By calling
     *                     setComplete(), we are ensuring that the session is cleaned up and
     *                     ready for a new order the next time the user creates a taco.
     * @return
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/orders";
    }

    @GetMapping
    public String getOrders(Model model){
        Iterable<TacoOrder> orders = service.findAllOrders();
        model.addAttribute("orders",orders);
        return "orders";

    }
}
