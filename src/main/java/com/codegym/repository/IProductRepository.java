package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    Iterable<Product> findAllByCategory(Category category);
    Iterable<Product> findAllByNameContaining(String name);
    Iterable<Product> findAllByOrderByPrice();
    @Query(value = "select * from products order by id desc limit 4", nativeQuery = true)
    Iterable<Product> find4Product();
    @Query( value = "select * from products where price between ?1 and ?2",nativeQuery = true)
    Iterable<Product> sortByPrice(Float price1,Float price2);
}
