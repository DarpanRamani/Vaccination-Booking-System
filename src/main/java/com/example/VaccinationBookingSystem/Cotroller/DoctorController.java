package com.example.VaccinationBookingSystem.Cotroller;

import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Service.DoctorService;
import com.example.VaccinationBookingSystem.dto.RequestDto.CenterRequestDto;
import com.example.VaccinationBookingSystem.dto.RequestDto.DoctorRequestDto;
import com.example.VaccinationBookingSystem.dto.RequestDto.UpdateDoctorEmailOrAgeRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.DoctorResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.DoctorWhoBelongsToParticularGivenCenterResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.DoctorWithHighestAppoinmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try {
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-age-greater-than")
    public List<String> getAgeGreaterThan(@RequestParam("newAge") int newAge){
        List<String> doctors = doctorService.getAgeGreaterThan(newAge);
        return doctors;
    }

    @GetMapping("/Doctor-with-highest-number-of-appointments")
    public ResponseEntity doctorWithHighestNumberOfAppointments(){
        try{
            DoctorWithHighestAppoinmentResponseDto doctor = doctorService.doctorWithHighestNumberOfAppointments();
            return new ResponseEntity(doctor,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    //MADE EXTRA API NOT TESTED
    @GetMapping("/Get-list-of-doctors-who-belong-to-particular-given-center")
    public ResponseEntity listOfDoctorsWhoBelongToParticularCenter(CenterRequestDto centerRequestDto){
        try{
            List<DoctorWhoBelongsToParticularGivenCenterResponseDto> list =
                    doctorService.listOfDoctorsWhoBelongToParticularCenter(centerRequestDto);
            return new ResponseEntity(list,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/Get-list-of-doctor-who-belong-to-particular-given-centerType")
    public ResponseEntity listOfDoctorWhoBelongToParticularGivenCenterType(@RequestParam("newCenterType")CenterType centerType){
        try{
            List<DoctorWhoBelongsToParticularGivenCenterResponseDto> list =
                    doctorService.listOfDoctorWhoBelongToParticularGivenCenterType(centerType);
            return new ResponseEntity(list,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/Update-email-and/or-age-of-doctor-2")
    public ResponseEntity updateEmailAndOrAgeOfDoctor(UpdateDoctorEmailOrAgeRequestDto updateDoctorEmailOrAgeRequestDto){
         try{
             String ans = doctorService.updateEmailAndOrAgeOfDoctor(updateDoctorEmailOrAgeRequestDto);
             return new ResponseEntity(ans,HttpStatus.ACCEPTED);
         }
         catch(Exception e){
             return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
         }
    }


    //Done get the doctor with highest number of appointments
    //Done get the list of doctors who belong to a patricular center
    //Done api to update email and/or age of a doctor
}
