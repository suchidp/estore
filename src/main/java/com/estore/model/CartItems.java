package com.estore.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@Table(name = "cart_Items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemsId;


    @OneToOne
    private Product product;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_tax")
    private double totalTax;


    @OneToOne
    //@JoinColumn(name = "user_id")
    private User userId;



    @OneToOne
    @JoinColumn(name = "price_id")
    private Price price;


    @OneToOne
    @JoinColumn(name = "product_inventory_id")
    private ProductInventory productInventory;


    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}