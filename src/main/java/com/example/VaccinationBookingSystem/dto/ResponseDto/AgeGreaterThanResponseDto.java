package com.example.VaccinationBookingSystem.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgeGreaterThanResponseDto {
    String name;
    String emailId;
}
