package com.example.VaccinationBookingSystem.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookAppointmentRequestDto {
    int doctorId;
    int personId;
}
