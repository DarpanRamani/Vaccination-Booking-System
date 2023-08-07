package com.example.VaccinationBookingSystem.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FemalesWhoTakenDose1ButNotDose2ResponseDto {
    String name;
    int age;
}
