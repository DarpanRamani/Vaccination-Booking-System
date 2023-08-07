package com.example.VaccinationBookingSystem.Model;

import com.example.VaccinationBookingSystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int age;

    @Column(unique = true)
    String emailId;

    @Enumerated(EnumType.STRING)
    Gender gender;

    boolean dose1Taken;

    boolean dose2Taken;


    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    List<Dose> dosesTaken = new ArrayList<>();

    @OneToOne(mappedBy = "person",cascade = CascadeType.ALL)
    Certificate certificate;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();
}
