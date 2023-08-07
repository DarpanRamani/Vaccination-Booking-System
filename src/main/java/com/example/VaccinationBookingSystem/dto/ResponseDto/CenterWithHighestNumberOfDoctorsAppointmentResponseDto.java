package com.example.VaccinationBookingSystem.dto.ResponseDto;

import com.example.VaccinationBookingSystem.Enum.CenterType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CenterWithHighestNumberOfDoctorsAppointmentResponseDto {
    String centerName;

    CenterType centerType;

    String address;

    int HighestDoctors;
}
