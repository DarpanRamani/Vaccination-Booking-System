package com.example.VaccinationBookingSystem.Repository;

import com.example.VaccinationBookingSystem.Model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,Integer> {
}
