package com.estore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;



    @Column(name = "total")
    private BigDecimal total;


    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    private List<CartItems> cartItems;

    @Column(name = "delivery_charge")
    private double deliveryCharge;


}
