package com.example.springmarket.model;

import com.example.springmarket.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "timeOfAppointment_id")
    private TimeOfAppointment timeOfAppointment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;






}