package com.example.task02.controller;

import com.example.task02.dto.ApiResponse;
import com.example.task02.dto.UserDTO;
import com.example.task02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse apiResponse=userService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.FOUND:HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse apiResponse=userService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.FOUND:HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @PostMapping
    public HttpEntity<?> save(@RequestBody UserDTO dto){
        ApiResponse apiResponse=userService.save(dto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody UserDTO dto){
        ApiResponse apiResponse=userService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse=userService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
}
