package com.example.VaccinationBookingSystem.Cotroller;


import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.Service.VaccinationCenterService;
import com.example.VaccinationBookingSystem.dto.RequestDto.CenterRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterWithHighestNumberOfDoctorsAppointmentResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.DoctorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService centerSevice;

    @PostMapping("/add")
    public CenterResponseDto addCenter(@RequestBody CenterRequestDto centerRequestDto){
        CenterResponseDto centerResponseDto = centerSevice.addCenter(centerRequestDto);
        return centerResponseDto;
    }

    @GetMapping("/get-all-doctors-at-centers-of-particular-centerType")
    public ResponseEntity getAllDoctorsOfParticularCenterType(@RequestParam("center")CenterType centerType){
        try {
            List<DoctorResponseDto> getAllDoctorOfParticularCenterTypeResponseDtos =
                    centerSevice.getAllDoctorsOfParticularCenterType(centerType);
            return new ResponseEntity(getAllDoctorOfParticularCenterTypeResponseDtos, HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-the-center-with-highest-number-of-doctors")
    public ResponseEntity GetTheCenterWithHighestNumberOfDoctors(){
        try{
            CenterWithHighestNumberOfDoctorsAppointmentResponseDto centerWithHighestNumberOfDoctorsAppointmentResponseDto =
                    centerSevice.GetTheCenterWithHighestNumberOfDoctors();
            return new ResponseEntity(centerWithHighestNumberOfDoctorsAppointmentResponseDto,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/get-the-center-with-highest-number-of-doctors-among-particular-centerType")
//    public ResponseEntity getTheCenterWithHighestNumberOfDoctorsAmongParticularCenterType(){
//        int count = 0;
//        count = centerSevice.getTheCenterWithHighestNumberOfDoctorsAmongParticularCenterType();
//        return new ResponseEntity(count,HttpStatus.FOUND);
//    }


    //Done get all the doctors at centers of a particular centerType
    //Done get the center with highest number of doctors
    //get the center with highest number of doctors among a particular centerType
}
