package com.example.VaccinationBookingSystem.exception;

public class DoseAlreadyTakenException extends RuntimeException{
     public DoseAlreadyTakenException(String message){
         super(message);
     }
}
