package com.example.VaccinationBookingSystem.dto.ResponseDto;

import com.example.VaccinationBookingSystem.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetDose1ResponseDto {
    DoseType doseType;

    String message;
}
