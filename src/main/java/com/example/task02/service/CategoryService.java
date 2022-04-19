package com.example.task02.service;

import com.example.task02.dto.ApiResponse;
import com.example.task02.entity.Category;
import com.example.task02.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;

    public ApiResponse getAll() {
        List<Category> all = categoryRepository.findAll();
        return new ApiResponse("List of categories",true,all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Xatolik",false);
        Category category = byId.get();
        return new ApiResponse("Mana",true,category);

    }

    public ApiResponse save(Category dto) {
        Category category= new Category();
        category.setName(dto.getName());
        Category save = categoryRepository.save(category);
        return new ApiResponse("Created",true,save);
    }

    public ApiResponse edit(Integer id, Category dto) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Xatolik",false);
        Category category = byId.get();
        category.setName(dto.getName());
        Category save = categoryRepository.save(category);
        return new ApiResponse("created",true,save);
    }

    public ApiResponse delete(Integer id) {

        categoryRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
