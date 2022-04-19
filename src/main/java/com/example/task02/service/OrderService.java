package com.example.task02.service;

import com.example.task02.dto.ApiResponse;
import com.example.task02.dto.OrderDTO;
import com.example.task02.entity.*;
import com.example.task02.entity.enums.PaymentType;
import com.example.task02.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    final OrderProductRepository orderProductRepository;
    final OrderRepository orderRepository;
    final PaymentRepository paymentRepository;
    final AddressRepository addressRepository;
    final UserRepository userRepository;
    final ProductRepository productRepository;

    public ApiResponse getAll() {
        List<Order> all = orderRepository.findAll();
return new ApiResponse("List of orders",true,all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Xatolik",false);
        Order order = byId.get();
        return new ApiResponse("Mana",true,order);
    }

    public ApiResponse delete(Integer id) {
        orderRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }

    public ApiResponse save(OrderDTO dto) {
        Order order= new Order();
        Address address=new Address();
        Optional<Address> byCityAndHomeAAndStreet = addressRepository.findByCityAndHomeAndStreet(dto.getCity(), dto.getHome(), dto.getStreet());
        if (byCityAndHomeAAndStreet.isEmpty()){
        address.setCity(dto.getCity());
        address.setHome(dto.getHome());
        address.setStreet(dto.getStreet());
            Address save = addressRepository.save(address);
        order.setAddress(save);
        }
        order.setAddress(byCityAndHomeAAndStreet.get());
        Payment payment= new Payment();
        payment.setPaymentType(PaymentType.valueOf(dto.getPaymentType()));
        Payment save = paymentRepository.save(payment);
        order.setPayment(save);
        Optional<User> byId = userRepository.findById(dto.getUserId());
        User user = byId.get();
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);

        int length = dto.getProductAmounts().length;
        List<OrderProduct> orderProducts= new ArrayList<>();
        Integer[] productAmounts = dto.getProductAmounts();
        Integer[] productIds = dto.getProductIds();
        for (int i=0;i<length;i++){
            OrderProduct orderProduct=new OrderProduct();
            orderProduct.setOrder(savedOrder);
            orderProduct.setProduct(productRepository.getById(productIds[i]));
            orderProduct.setAmount(productAmounts[i]);
            OrderProduct save1 = orderProductRepository.save(orderProduct);
            orderProducts.add(save1);
        }
        List<OrderProduct> orderProducts1 = orderProductRepository.saveAll(orderProducts);
        return new ApiResponse("Saqlandi",true,savedOrder);
    }
}
