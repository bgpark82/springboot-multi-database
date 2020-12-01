package com.example.demo.controller;

import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductRepository;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @GetMapping("/user/{id}")
    public String hello(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        Product product = productRepository.findById(id).get();
        return user.getName() + " " + product.getName();
    }
}
