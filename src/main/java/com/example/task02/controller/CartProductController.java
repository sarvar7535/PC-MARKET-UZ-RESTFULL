package com.example.task02.controller;

import com.example.task02.dto.ApiResponse;
import com.example.task02.dto.CartDTO;
import com.example.task02.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartProduct")
@RequiredArgsConstructor
public class CartProductController {
    final CartProductService cartProductService;


    @GetMapping
    public HttpEntity<?> getAll(@RequestParam Integer cartId){
        ApiResponse apiResponse= cartProductService.getAll(cartId);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.FOUND:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id,@RequestParam Integer cartId){
        ApiResponse apiResponse= cartProductService.getOne(id,cartId);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.FOUND:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody CartDTO dto){
        ApiResponse apiResponse= cartProductService.save(dto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id,@RequestParam Integer cartId){
        ApiResponse apiResponse= cartProductService.delete(id,cartId);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
}
