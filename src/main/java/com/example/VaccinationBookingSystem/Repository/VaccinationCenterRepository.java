package com.example.VaccinationBookingSystem.Repository;

import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter,Integer> {

//    @Query(value = "SELECT center_id ,COUNT(email_id) AS num_doctors FROM doctor WHERE center_type IN (PRIVATE_HOSPITAL,GOVT_HOSPITAL,NGO) GROUP BY center_id ORDER BY num_doctors DESC LIMIT 1",nativeQuery = true)
//    int getTheCenterWithHighestNumberOfDoctorsAmongParticularCenterType();
}
