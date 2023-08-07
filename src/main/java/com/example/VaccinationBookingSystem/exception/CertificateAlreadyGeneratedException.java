package com.example.VaccinationBookingSystem.exception;

public class CertificateAlreadyGeneratedException extends RuntimeException{
    public CertificateAlreadyGeneratedException(String message){
        super(message);
    }
}
