package com.example.VaccinationBookingSystem.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddPersonResponseDto {

    String name;

    String message;
}
