package com.example.task02.service;

import com.example.task02.dto.ApiResponse;
import com.example.task02.entity.Product;
import com.example.task02.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    public ApiResponse getAll() {
        return new ApiResponse("List of products",true,productRepository.findAll());
    }

    public ApiResponse getOne(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Xatolik",false);
        Product product = byId.get();
        return new ApiResponse("Mana",true,product);
    }

    public ApiResponse delete(Integer id) {
        productRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }

    public ApiResponse save(Product dto) {
        Product product= new Product();
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setName(dto.getName());
        Product save = productRepository.save(product);
        return new ApiResponse("Created",true,save);
    }

    public ApiResponse edit(Integer id, Product dto) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Xatolik",false);
        Product product = byId.get();
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setName(dto.getName());
        Product save = productRepository.save(product);
        return new ApiResponse("Edited",true,save);
    }
}
