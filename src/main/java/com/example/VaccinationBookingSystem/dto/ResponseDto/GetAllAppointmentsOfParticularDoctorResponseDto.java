package com.example.VaccinationBookingSystem.dto.ResponseDto;

import com.example.VaccinationBookingSystem.Model.Appointment;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetAllAppointmentsOfParticularDoctorResponseDto {
    List<Appointment> appointments = new ArrayList<>();
    String appointmentId;
}
