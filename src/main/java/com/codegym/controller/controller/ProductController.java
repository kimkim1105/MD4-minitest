package com.codegym.controller.controller;

import com.codegym.model.Product;
import com.codegym.model.Category;
import com.codegym.service.IProductService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> showList(){
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()){
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> findProductByName(@RequestParam String name) {
        List<Product> products = (List<Product>) productService.findAllByNameContaining(name);
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/odersByPrice")
    public ResponseEntity<Iterable<Product>> oderProduct() {
        List<Product> products = (List<Product>) productService.findAllByOrderByPrice();
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/get4")
    public ResponseEntity<Iterable<Product>> get4Product() {
        List<Product> products = (List<Product>) productService.find4Product();
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/sort")
    public ResponseEntity<Iterable<Product>> sortByPrice(@RequestParam("price1") Float price1,@RequestParam("price2") Float price2) {
        List<Product> products = (List<Product>) productService.sortByPrice(price1,price2);
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/viewByCategory/{id}")
    public ResponseEntity<Iterable<Product>> viewProductByCategory(@PathVariable Long id) {
        List<Product> products = (List<Product>) productService.findAllByCategory(categoryService.findById(id).get());
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
