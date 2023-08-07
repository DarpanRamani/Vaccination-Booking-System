package com.example.VaccinationBookingSystem.dto.RequestDto;

import com.example.VaccinationBookingSystem.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddPersonRequestDto {
    String name;

    int age;

    String emailId;

    Gender gender;
}
