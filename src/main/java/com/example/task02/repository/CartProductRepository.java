package com.example.task02.repository;

import com.example.task02.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
List<CartProduct> findAllByCart_Id(Integer id);
}