package com.example.VaccinationBookingSystem.dto.RequestDto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateDoctorEmailOrAgeRequestDto {
    int newAge;

    String oldEmailId;

    String newEmailId;
}
