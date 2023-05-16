package com.estore.repository;

import com.estore.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
}
