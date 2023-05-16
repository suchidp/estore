package com.estore.repository;

;
import com.estore.model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffersRepository extends JpaRepository<Offers, Integer> {
}
