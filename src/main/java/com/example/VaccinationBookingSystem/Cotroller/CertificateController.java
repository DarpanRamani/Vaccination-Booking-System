package com.example.VaccinationBookingSystem.Cotroller;

import com.example.VaccinationBookingSystem.Service.CertificateService;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CertificateResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    CertificateService certificateService;

    @GetMapping("/certificate-generation")
    public ResponseEntity getCertificate(@RequestParam("id") int personId){
        try{
            CertificateResponseDto certificateResponseDto = certificateService.getCertificate(personId);
            return new ResponseEntity(certificateResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
