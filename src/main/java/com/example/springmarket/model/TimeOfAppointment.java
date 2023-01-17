package com.example.springmarket.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name = "time_of_appointments")
public class TimeOfAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date;

    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public TimeOfAppointment(String date, Doctor doctor) {
        this.date = date;

        this.doctor = doctor;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "timeOfAppointment")
    private List<Cart> list;

}
