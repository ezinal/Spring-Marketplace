package com.tr.obss.jss.jss_marketplace.service.impl;

import com.tr.obss.jss.jss_marketplace.exception.InvalidBlacklistOperation;
import com.tr.obss.jss.jss_marketplace.exception.UserIsNotSellerType;
import com.tr.obss.jss.jss_marketplace.model.Product;
import com.tr.obss.jss.jss_marketplace.model.Role;
import com.tr.obss.jss.jss_marketplace.model.RoleType;
import com.tr.obss.jss.jss_marketplace.model.User;
import com.tr.obss.jss.jss_marketplace.repository.ProductRepository;
import com.tr.obss.jss.jss_marketplace.repository.RoleRepository;
import com.tr.obss.jss.jss_marketplace.repository.UserRepository;
import com.tr.obss.jss.jss_marketplace.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Boolean existsByEmail(String email) {
        Objects.requireNonNull(email, "username cannot be null");
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        Objects.requireNonNull(id, "id cannot be null");
        return userRepository.getById(id);
    }

    @Override
    public List<User> getUsers(Integer page, Integer maxResult) {
        Objects.requireNonNull(page, "page cannot be null");
        Objects.requireNonNull(maxResult, "max_result cannot be null");
        return userRepository.findAll(PageRequest.of(page, maxResult)).getContent();
    }

    @Override
    public User createNewUser(User user) {
        Objects.requireNonNull(user, "user cannot be null.");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Objects.requireNonNull(user, "user cannot be null.");
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUserByName(String name) {
        return null;
    }

    @Override
    public void addRole(Long userId, Role role) {
        User user = getUserById(userId);
        Role userRole = roleRepository.findByName(role.getName());
        user.addRole(userRole);
        userRepository.save(user);
    }

    @Override
    public void removeRole(Long userId, Role role) {
        Objects.requireNonNull(userId);

        User user = getUserById(userId);
        Role userRole = roleRepository.findByName(role.getName());
        user.removeRole(userRole);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        Objects.requireNonNull(id);
        userRepository.removeUserById(id);
    }

    @Override
    public void addToFavorites(Long userId, Long productId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(productId);

        User user = userRepository.getById(userId);
        Product product = productRepository.getById(productId);

        user.addFavorite(product);
        userRepository.save(user);
    }

    @Override
    public void removeFromFavorites(Long userId, Long productId) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(productId);

        User user = userRepository.getById(userId);
        Product product = productRepository.getById(productId);

        user.removeFavorite(product);
        userRepository.save(user);
    }

    @Override
    public void addToBlacklist(Long userId, Long sellerId) throws InvalidBlacklistOperation, UserIsNotSellerType {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(sellerId);

        if(userId.equals(sellerId)) {
            throw new InvalidBlacklistOperation();
        }

        User seller = userRepository.getById(sellerId);
        Role sellerRole = roleRepository.findByName(RoleType.ROLE_SELLER);
        if (!seller.getRoles().contains(sellerRole)) {
            throw new UserIsNotSellerType();
        }
        User user = userRepository.getById(userId);

        user.addBlacklist(seller);
        userRepository.save(user);
    }

    @Override
    public void removeFromBlacklist(Long userId, Long sellerId) throws InvalidBlacklistOperation {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(sellerId);

        if(userId.equals(sellerId)) {
            throw new InvalidBlacklistOperation();
        }

        User user = userRepository.getById(userId);
        User seller = userRepository.getById(sellerId);

        user.removeBlacklist(seller);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
