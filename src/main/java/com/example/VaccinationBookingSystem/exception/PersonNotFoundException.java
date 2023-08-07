package com.example.VaccinationBookingSystem.exception;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(String message){
        super(message);
    }
}
