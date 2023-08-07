package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookDose1RequestDto;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookDose2RequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.GetDose1ResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.GetDose2ResponseDto;
import com.example.VaccinationBookingSystem.exception.Dose1NotTakenException;
import com.example.VaccinationBookingSystem.exception.DoseAlreadyTakenException;
import com.example.VaccinationBookingSystem.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    PersonRepository personRepository;
//    public Dose getDose1(int personId, DoseType doseType) {
//        Optional<Person> optionalPerson = personRepository.findById(personId);
//        //Check if person exist or not
//        if(!optionalPerson.isPresent()){
//             throw new PersonNotFoundException("Invalid personId");
//        }
//        //Check if dose 1 is already taken
//        Person person = optionalPerson.get();
//        if(person.isDose1Taken()){
//            throw new DoseAlreadyTakenException("Dose 1 Already Taken");
//        }
//        //create dose
//        Dose dose = new Dose();
//        dose.setDoseId(String.valueOf(UUID.randomUUID()));
//        dose.setDoseType(doseType);
//        dose.setPerson(person);
//
//        person.setDose1Taken(true);
//        person.getDosesTaken().add(dose);
//       Person savedPerson = personRepository.save(person);
//       return savedPerson.getDosesTaken().get(0);
//    }
    public GetDose1ResponseDto getDose1(BookDose1RequestDto bookDose1RequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDose1RequestDto.getPersonId());
        //Check if person exist or not
        if(!optionalPerson.isPresent()){
            throw new PersonNotFoundException("Invalid PersonId");
        }
        //Check if dose 1 is already taken
        Person person = optionalPerson.get();
        if(person.isDose1Taken()){
            throw new DoseAlreadyTakenException("Dose 1 Already Taken");
        }
        //create dose from request DTO
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1RequestDto.getDoseType());
        dose.setPerson(person);
        person.setDose1Taken(true);
        person.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person);
//        return savedPerson.getDosesTaken().get(0);
        GetDose1ResponseDto getDose1ResponseDto = new GetDose1ResponseDto();
        getDose1ResponseDto.setDoseType(dose.getDoseType());
        getDose1ResponseDto.setMessage("You have Successfully taken Dose1");

        return getDose1ResponseDto;
    }

    public GetDose2ResponseDto getDose2(BookDose2RequestDto bookDose2RequestDto){
        Optional<Person> optionalPerson = personRepository.findById(bookDose2RequestDto.getPersonId());
        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("Invalid PersonId");
        }
        Person person = optionalPerson.get();
        if(!person.isDose1Taken()){
            throw new Dose1NotTakenException("Dose 1 not taken Please Take Dose 1 first");
        }
        if(person.isDose2Taken()){
            throw new DoseAlreadyTakenException("Dose 2 Already Taken");
        }
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose2RequestDto.getDoseType());
        dose.setPerson(person);
        person.setDose2Taken(true);
        person.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person);

        //prepare getdose2response dto
        GetDose2ResponseDto getDose2ResponseDto = new GetDose2ResponseDto();
        getDose2ResponseDto.setDoseType(dose.getDoseType());
        getDose2ResponseDto.setMessage("You have Successfully Taken the Dose2");
        return getDose2ResponseDto;
    }
}
