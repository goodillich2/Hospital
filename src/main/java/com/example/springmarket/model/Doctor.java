package com.example.springmarket.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url")
    private String imageUrl;

    private String name;

    private String surname;

    private String description;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<TimeOfAppointment> timeOfAppointments;




}
