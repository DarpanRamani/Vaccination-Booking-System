package com.example.VaccinationBookingSystem.exception;

public class PersonListIsEmptyException extends RuntimeException{
    public PersonListIsEmptyException(String message){
        super(message);
    }
}
