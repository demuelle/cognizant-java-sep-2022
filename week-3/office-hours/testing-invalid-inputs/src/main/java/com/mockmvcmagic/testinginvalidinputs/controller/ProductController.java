package com.mockmvcmagic.testinginvalidinputs.controller;

import com.mockmvcmagic.testinginvalidinputs.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProductToInventory(@RequestBody Product product) {
        System.out.println("Yes! Now we have " + product);
        return product;
    }
}
