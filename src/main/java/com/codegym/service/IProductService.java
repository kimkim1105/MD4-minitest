package com.codegym.service;

import com.codegym.model.Category;
import com.codegym.model.Product;

public interface IProductService extends IGeneralService<Product>{
    Iterable<Product> findAllByCategory(Category category);
    Iterable<Product> findAllByNameContaining(String name);
    Iterable<Product> findAllByOrderByPrice();
    Iterable<Product> find4Product();
    Iterable<Product> sortByPrice(Float price1,Float price2);
}
