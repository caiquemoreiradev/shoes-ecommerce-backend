package com.example.store.domain.product;

public record RequestProduct(String id, String name, Float price, Float old_price, String image, Boolean on_promo, Integer[] sizes, String stripePriceId) {
}
