package com.example.springmarket.dto.cart;


import com.example.springmarket.model.Cart;
import com.example.springmarket.model.TimeOfAppointment;
import com.example.springmarket.repository.TimeOfAppointmentRepository;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CartItemDto {
    private Integer id;
    private TimeOfAppointment timeOfAppointment;

    public CartItemDto(Cart cart) {
        this.id = cart.getId();
        this.setTimeOfAppointment(cart.getTimeOfAppointment());
    }

}

