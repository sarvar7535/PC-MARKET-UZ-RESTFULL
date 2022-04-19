package com.example.task02.service;

import com.example.task02.dto.ApiResponse;
import com.example.task02.dto.UserDTO;
import com.example.task02.entity.Address;
import com.example.task02.entity.Cart;
import com.example.task02.entity.User;
import com.example.task02.repository.AddressRepository;
import com.example.task02.repository.CartRepository;
import com.example.task02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
final UserRepository userRepository;
final CartRepository cartRepository;
final AddressRepository addressRepository;

    public ApiResponse getAll() {

        List<User> all = userRepository.findAll();
        return new ApiResponse("List of users",true,all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) return  new ApiResponse("Xatolik",false);
        return new ApiResponse("Mana",true,byId.get());
    }

    public ApiResponse save(UserDTO dto) {
        Address address= new Address();
        address.setCity(dto.getCity());
        address.setHome(dto.getHome());
        address.setStreet(dto.getStreet());
        Address savedAddress = addressRepository.save(address);
        User user= new User();
        user.setFullName(dto.getFullName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(savedAddress);
        Cart cart= new Cart();
        Cart save1 = cartRepository.save(cart);
        user.setCart(save1);
        User save = userRepository.save(user);
        return new ApiResponse("Created",true,save);
    }
    public ApiResponse edit(Integer id,UserDTO dto) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Xatolik",false);
        User user = byId.get();
        Address address= new Address();
        address.setCity(dto.getCity());
        address.setHome(dto.getHome());
        address.setStreet(dto.getStreet());
        Address savedAddress = addressRepository.save(address);
        user.setFullName(dto.getFullName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(savedAddress);
        User save = userRepository.save(user);
        return new ApiResponse("Edited",true,save);
    }

    public ApiResponse delete(Integer id) {
        userRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }



}
