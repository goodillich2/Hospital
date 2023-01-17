package com.example.springmarket.controller2;

import com.example.springmarket.dto.cart.AddToCartDto;
import com.example.springmarket.dto.cart.CartDto;
import com.example.springmarket.model.user.User;
import com.example.springmarket.response.ApiResponse;
import com.example.springmarket.service.AuthSessionIdService;
import com.example.springmarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class AppointmentController {

    @Autowired
    private CartService cartService;

    @Autowired
    AuthSessionIdService authSessionIdService;

    // post cart api
    @PostMapping("/add")
    public String  addToCart(@RequestParam("doctorId") Integer doctorId,  @RequestParam("timeOfAppointmentId") Integer timeOfAppointmentId,  HttpSession session) throws Exception {
        AddToCartDto addToCartDto = new AddToCartDto(timeOfAppointmentId);
        System.out.println(addToCartDto);


        // find the user
        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);


        cartService.addToCart(addToCartDto, user);

        return "redirect:/timeOfAppointment/"+doctorId;
    }


    // get all cart items for a user
    @GetMapping("/")
    public String getCartItems(HttpSession session, Model model) throws Exception {
        // find the user
        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);

        // get cart items

        CartDto cartDto = cartService.listCartItems(user);
        model.addAttribute("cartDto", cartDto);
        String mess;
        mess = "Всі записи для користувача " + user.getFirstName() + " " + user.getLastName() + ":";
        model.addAttribute("mess", mess);
        return "cartItems";
    }

    // delete a cart item for a user

    @PostMapping("/delete/{cartItemId}")
    public String deleteCartItem(@PathVariable("cartItemId") Integer itemId, HttpSession session) throws Exception {

        // find the user
        String sessionId = session.getId();
        User user = authSessionIdService.getUser(sessionId);


        cartService.deleteCartItem(itemId, user);

        return "redirect:/cart/";

    }

}
