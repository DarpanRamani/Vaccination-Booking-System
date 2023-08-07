package com.example.VaccinationBookingSystem.Cotroller;

import com.example.VaccinationBookingSystem.Enum.DoseType;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Service.DoseService;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookDose1RequestDto;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookDose2RequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.GetDose1ResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.GetDose2ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseContoller {

    @Autowired
    DoseService doseService;

//    @PostMapping("/get_dose_1")
//    public ResponseEntity getDose1(@RequestParam("id") int personId, @RequestParam("doseType")DoseType doseType){
//        try {
//            Dose doseTake = doseService.getDose1(personId, doseType);
//            return new ResponseEntity(doseTake,HttpStatus.CREATED);
//        }
//        catch(Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
    @PostMapping("/get_dose_1")
    public ResponseEntity getDose1(@RequestBody BookDose1RequestDto bookDose1RequestDto){
        try {
            GetDose1ResponseDto getDose1ResponseDto = doseService.getDose1(bookDose1RequestDto);
            return new ResponseEntity(getDose1ResponseDto,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get_dose_2")
    public ResponseEntity getDose2(@RequestBody BookDose2RequestDto bookDose2RequestDto){
        try{
           GetDose2ResponseDto getDose2ResponseDto = doseService.getDose2((bookDose2RequestDto));
            return new ResponseEntity(getDose2ResponseDto,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
