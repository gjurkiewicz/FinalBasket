package com.jurkiewicz.grzegorz.FinalBasket.repository;

import com.jurkiewicz.grzegorz.FinalBasket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
    User findUserByName(String name);
    User findByEmail(String email);
}
