package com.example.VaccinationBookingSystem.dto.ResponseDto;

import com.example.VaccinationBookingSystem.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetAllPersonFullyVaccinatedResponseDto {
    String name;

    int age;

    String emailId;

}
