package com.jurkiewicz.grzegorz.FinalBasket.repository;

import com.jurkiewicz.grzegorz.FinalBasket.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByRole(String role);
}
