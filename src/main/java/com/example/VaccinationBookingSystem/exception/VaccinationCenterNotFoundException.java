package com.example.VaccinationBookingSystem.exception;

public class VaccinationCenterNotFoundException extends RuntimeException{
    public VaccinationCenterNotFoundException(String message){
        super(message);
    }
}
