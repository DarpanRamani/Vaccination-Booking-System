package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Model.Certificate;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repository.CertificateRepository;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CertificateResponseDto;
import com.example.VaccinationBookingSystem.exception.CertificateAlreadyGeneratedException;
import com.example.VaccinationBookingSystem.exception.Dose1NotTakenException;
import com.example.VaccinationBookingSystem.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    PersonRepository personRepository;
    public CertificateResponseDto getCertificate(int personId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("PersonId is Invalid");
        }
        Person person = optionalPerson.get();
        if(!person.isDose1Taken() && !person.isDose2Taken())
        {
            throw new Dose1NotTakenException("Person Not Take Even Single Dose");
        }
        if(!person.isDose1Taken()){
            throw new Dose1NotTakenException("Person has not taken Dose1");
        }
        if(!person.isDose2Taken()){
            throw new Dose1NotTakenException("Person has not taken Dose2");
        }
        if(person.getCertificate() != null){
            throw new CertificateAlreadyGeneratedException("Certificate Already Generated for These personId");
        }

        Certificate certificate = new Certificate();
        certificate.setCertificateNo(String.valueOf(UUID.randomUUID()));
        certificate.setConfirmationMessage("Congratulations for Taking Both Doses your Certificate Generated Successfully");
        certificate.setPerson(person);

        person.setCertificate(certificate);
        Person savedPerson = personRepository.save(person);

//        String text = "Congratulations for Taking Both Doses Your Certificate is attached below";
//
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("darpanacciojob@gmail.com");
//        simpleMailMessage.setTo(savedPerson.getEmailId());
//        simpleMailMessage.setSubject("Vaccine Certificate");
//        simpleMailMessage.setText(text);

        CertificateResponseDto certificateResponseDto = new CertificateResponseDto();
        certificateResponseDto.setCertificateNo(certificate.getCertificateNo());
        certificateResponseDto.setPersonName(savedPerson.getName());
        certificateResponseDto.setConfirmationMessage(certificate.getConfirmationMessage());
        return certificateResponseDto;
    }
}
