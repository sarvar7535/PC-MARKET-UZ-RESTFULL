package com.example.task02.service;

import com.example.task02.dto.ApiResponse;
import com.example.task02.dto.CartDTO;
import com.example.task02.entity.Cart;
import com.example.task02.entity.CartProduct;
import com.example.task02.entity.Product;
import com.example.task02.repository.CartProductRepository;
import com.example.task02.repository.CartRepository;
import com.example.task02.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartProductService {
    final CartRepository cartRepository;
    final CartProductRepository cartProductRepository;
    final ProductRepository productRepository;


    public ApiResponse getAll(Integer cartId) {
        List<CartProduct> allByCart_id = cartProductRepository.findAllByCart_Id(cartId);
        return new ApiResponse("List",true,allByCart_id);
    }

    public ApiResponse getOne(Integer id, Integer cartId) {
        List<CartProduct> allByCart_id = cartProductRepository.findAllByCart_Id(cartId);
        for (CartProduct cartProduct : allByCart_id) {
            if (cartProduct.getId().equals(id)){
                return new ApiResponse("mana",true,cartProduct);
            }
        }
        return new ApiResponse("Xatolik",false);
    }

    public ApiResponse save(CartDTO dto) {
        Optional<Cart> byId = cartRepository.findById(dto.getCartId());
        if (byId.isEmpty()) return new ApiResponse("Xatolik",false);
        Cart cart = byId.get();
        List<CartProduct> cartProducts = cart.getCartProducts();
        CartProduct cartProduct= new CartProduct();
        Product byId1 = productRepository.getById(dto.getProductId());
        for (CartProduct product : cartProducts) {
            if (product.equals(byId1)){
                product.setAmount(product.getAmount()+dto.getAmount());
            }
        }
        cartProduct.setProduct(byId1);
        cartProduct.setAmount(dto.getAmount());
        cartProducts.add(cartProduct);
        cart.setCartProducts(cartProducts);
        Cart save = cartRepository.save(cart);
        return new ApiResponse("Saqlandi",true,save);
    }

    public ApiResponse delete(Integer id, Integer cartId) {
        Cart cart = cartRepository.getById(cartId);
        List<CartProduct> cartProducts = cart.getCartProducts();
        cartProducts.remove(cartProductRepository.getById(id));
        cart.setCartProducts(cartProducts);
        cartProductRepository.deleteById(id);
        Cart save = cartRepository.save(cart);
        return new ApiResponse("Deleted",true,save);
    }
}
