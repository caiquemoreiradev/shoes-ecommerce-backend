package com.example.store.controllers;

import com.example.store.domain.product.Product;
import com.example.store.domain.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.example.store.domain.product.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getProducts () {

        var allProducts = repository.findAllByActiveTrue();

        return  ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid RequestProduct data) {

        Product newProduct = new Product(data);

        repository.save(newProduct);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {

        Optional<Product> optionalProduct = repository.findById(data.id());

       if(optionalProduct.isPresent()) {

           Product product = optionalProduct.get();

           product.setName(data.name());
           product.setImage(data.image());
           product.setPrice(data.price());
           product.setOld_price(data.old_price());
           product.setOn_promo(data.on_promo());
           product.setSizes(data.sizes());
           product.setStripePriceId((data.stripePriceId()));

           return ResponseEntity.ok(product);
       }

       else {

           return  ResponseEntity.notFound().build();
       }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id) {

        Optional<Product> optionalProduct = repository.findById(id);

        if(optionalProduct.isPresent()) {

            Product product = optionalProduct.get();

            product.setActive(false);

            return ResponseEntity.noContent().build();
        }

        else {

            throw new EntityNotFoundException();
        }
    }
}
