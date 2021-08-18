package com.tr.obss.jss.jss_marketplace.service;

import com.tr.obss.jss.jss_marketplace.model.Role;
import com.tr.obss.jss.jss_marketplace.model.User;

import java.util.List;

public interface UserService {

    Boolean existsByEmail(String email);

    User getUserById(Long id);

    List<User> getUsers(Integer page, Integer max_result);

    User createNewUser(User user);

    User updateUser(User user);

    List<User> searchUserByName(String name);

    void addRole(Long userId, Role role);

    void removeRole(Long userId, Role role);

    void deleteUserById(Long id);

    void addToFavorites(Long userId, Long productId);

    void removeFromFavorites(Long userId, Long productId);

    void addToBlacklist(Long userId, Long sellerId);

    void removeFromBlacklist(Long userId, Long sellerId);

    User findByUsername(String username);
}
