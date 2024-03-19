package com.example.store.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Float price;

    private Float old_price;

    private String image;

    private Boolean active;

    private Boolean on_promo;

    private Integer[] sizes;

    private String stripePriceId;

    public Product (RequestProduct requestProduct) {

        this.name = requestProduct.name();
        this.image = requestProduct.image();
        this.price = requestProduct.price();
        this.on_promo = requestProduct.on_promo();
        this.old_price = requestProduct.old_price();
        this.sizes = requestProduct.sizes();
        this.stripePriceId = requestProduct.stripePriceId();
        this.active = true;
    }
}
