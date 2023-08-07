package com.example.VaccinationBookingSystem.Cotroller;

import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Service.AppointmentService;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookAppointmentRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.BookAppointmentResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.GetAllAppointmentsOfParticularDoctorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody BookAppointmentRequestDto bookAppointmentRequestDto){
        try {
            BookAppointmentResponseDto bookAppointmentResponseDto = appointmentService.bookAppointment(bookAppointmentRequestDto);
            return new ResponseEntity(bookAppointmentResponseDto,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/get-all-appointments-of-particular-doctor")
//    public ResponseEntity GetAllAppointmentOfParticularDoctor(@RequestParam("doctor")Doctor doctor){
//        try{
//            GetAllAppointmentsOfParticularDoctorResponseDto getAllAppointmentsOfParticularDoctorResponseDto =
//                    appointmentService.GetAllAppointmentOfParticularDoctor(doctor);
//            return new ResponseEntity(getAllAppointmentsOfParticularDoctorResponseDto,HttpStatus.FOUND);
//        }
//        catch(Exception e){
//            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
//        }
//    }

    //get all the appointments of a particular doctor
    //get all the appointments for a particular person
}
