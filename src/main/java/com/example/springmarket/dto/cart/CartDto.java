package com.example.springmarket.dto.cart;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CartDto {
    private List<CartItemDto> cartItems;
}
