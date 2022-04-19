package com.example.task02.controller;

import com.example.task02.dto.ApiResponse;
import com.example.task02.entity.Category;
import com.example.task02.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;


    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse apiResponse=categoryService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.FOUND:HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse apiResponse=categoryService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.FOUND:HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @PostMapping
    public HttpEntity<?> save(@RequestBody Category dto){
        ApiResponse apiResponse=categoryService.save(dto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody Category dto){
        ApiResponse apiResponse=categoryService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse=categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
}
