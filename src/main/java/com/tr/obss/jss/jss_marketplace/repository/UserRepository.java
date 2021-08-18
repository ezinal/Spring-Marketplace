package com.tr.obss.jss.jss_marketplace.repository;

import com.tr.obss.jss.jss_marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    void removeUserById(Long id);

    Boolean existsByEmail(String email);

    User findByUsername(String username);
}
