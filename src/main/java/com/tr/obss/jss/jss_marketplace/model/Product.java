package com.tr.obss.jss.jss_marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Product extends BaseEntity {

    @NotBlank
    @Size(max = 80)
    private String name;

    @URL
    private String imageUrl = "empty_box.jpeg";

    @NotNull
    private Integer price;

    @NotNull
    private Integer quantity;
}
