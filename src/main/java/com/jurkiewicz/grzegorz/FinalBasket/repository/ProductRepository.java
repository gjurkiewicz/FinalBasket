package com.jurkiewicz.grzegorz.FinalBasket.repository;

import com.jurkiewicz.grzegorz.FinalBasket.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository <Product, Long> {
}
