package com.tr.obss.jss.jss_marketplace.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
//@MappedSuperclass
@Entity
public class User extends BaseEntity {

    @NotNull
    @Size(max = 20)
    private String username;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Size(max = 20)
    private String city;

    @Size(max = 20)
    private String country;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)  // prevents setter method generation by lombok
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<Product> favorites = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<User> blacklist = new ArrayList<>();

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public void addFavorite(Product product) {
        this.favorites.add(product);
    }

    public void removeFavorite(Product product) {
        this.favorites.remove(product);
    }

    public void addBlacklist(User user) {
        this.blacklist.add(user);
    }

    public void removeBlacklist(User user) {
        this.blacklist.remove(user);
    }
}
