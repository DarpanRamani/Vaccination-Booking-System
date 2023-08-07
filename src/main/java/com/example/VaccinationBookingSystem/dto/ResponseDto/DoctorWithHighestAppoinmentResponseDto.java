package com.example.VaccinationBookingSystem.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorWithHighestAppoinmentResponseDto {
    String name;

    int age;

    String emailId;

    int HighestAppointment;
}
