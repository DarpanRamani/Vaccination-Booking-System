package com.example.VaccinationBookingSystem.exception;

public class DoctorListIsEmptyException extends RuntimeException{
    public DoctorListIsEmptyException(String message){
        super(message);
    }
}
