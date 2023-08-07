package com.example.VaccinationBookingSystem.Repository;

import com.example.VaccinationBookingSystem.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
