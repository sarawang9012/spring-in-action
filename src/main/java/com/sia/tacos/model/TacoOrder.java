package com.sia.tacos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TacoOrder
 * Package: com.sia.tacos
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/15 11:42
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "taco_order")
public class TacoOrder implements Serializable {
    private static final long serialVersionID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @NotBlank(message = "Delivery name is required")
    @Column(name = "delivery_name")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    @Column(name = "delivery_street")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    @Column(name = "delivery_city")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    @Size(max=2, message = "State must be no more than 2 characters " +
            "long")
    @Column(name = "delivery_state")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    @Column(name = "delivery_zip")
    private String deliveryZip;

    //Luhn algorithm check (https://creditcardvalidator.org/articles/luhn-algorithm).
//    @CreditCardNumber(message = "Not a valid credit card number")
    @Column(name = "cc_number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted " +
            "MM/YY")
    @Column(name = "cc_expiration")
    private String ccExpiration;

    @Column(name = "cc_cvv")
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @CreationTimestamp
    @Column(name = "placed_at", updatable = false, nullable = false)
    private  LocalDateTime placedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tacoOrder")
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }

}
