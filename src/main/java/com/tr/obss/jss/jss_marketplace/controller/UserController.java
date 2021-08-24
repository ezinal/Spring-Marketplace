package com.tr.obss.jss.jss_marketplace.controller;

import com.tr.obss.jss.jss_marketplace.model.Role;
import com.tr.obss.jss.jss_marketplace.model.User;
import com.tr.obss.jss.jss_marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<User> getAllUsers(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "max_result", defaultValue = "10") Integer maxResult) {
        return userService.getUsers(page, maxResult);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @PatchMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody User user) {
        userService.deleteUserById(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}/role")
    public void assignRole(@PathVariable Long id, @RequestBody Role userRole) {
        userService.addRole(id, userRole);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/role")
    public void removeRole(@PathVariable Long id, @RequestBody Role userRole) {
        userService.removeRole(id, userRole);
    }

    @PostMapping("/{userId}/favorites/{productId}")
    public void addToFavorites(@PathVariable Long userId, @PathVariable Long productId) {
        userService.addToFavorites(userId, productId);
    }

    @DeleteMapping("/{userId}/favorites/{productId}")
    public void removeFromFavorites(@PathVariable Long userId, @PathVariable Long productId) {
        userService.removeFromFavorites(userId, productId);
    }

    @PostMapping("/{userId}/blacklist/{sellerId}")
    public void addToBlacklist(@PathVariable Long userId, @PathVariable Long sellerId) {
        userService.addToBlacklist(userId, sellerId);
    }

    @DeleteMapping("/{userId}/blacklist/{sellerId}")
    public void removeFromBlacklist(@PathVariable Long userId, @PathVariable Long sellerId) {
        userService.removeFromBlacklist(userId, sellerId);
    }
}
