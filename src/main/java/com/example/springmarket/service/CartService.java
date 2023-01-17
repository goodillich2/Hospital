package com.example.springmarket.service;


import com.example.springmarket.dto.cart.AddToCartDto;
import com.example.springmarket.dto.cart.CartDto;
import com.example.springmarket.dto.cart.CartItemDto;
import com.example.springmarket.model.Cart;

import com.example.springmarket.model.TimeOfAppointment;
import com.example.springmarket.model.user.User;
import com.example.springmarket.repository.CartRepository;
import com.example.springmarket.repository.TimeOfAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    TimeOfAppointmentRepository timeOfAppointmentRepository;

    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, User user) throws Exception {

        // validate if the product id is valid
        TimeOfAppointment timeOfAppointment = timeOfAppointmentRepository.findById(addToCartDto.getTimeOfAppointmentId()).get();

        Cart cart = new Cart();
        cart.setTimeOfAppointment(timeOfAppointment);
        cart.setUser(user);
        cart.setCreatedDate(new Date());


        // save the cart
        cartRepository.save(cart);

    }


    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart: cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setCartItems(cartItems);
        return  cartDto;
    }



    public void deleteCartItem(Integer cartItemId, User user) {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        Cart cart = optionalCart.get();
        cartRepository.delete(cart);


    }
}
