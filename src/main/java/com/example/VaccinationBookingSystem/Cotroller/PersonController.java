package com.example.VaccinationBookingSystem.Cotroller;

import com.example.VaccinationBookingSystem.Service.PersonService;
import com.example.VaccinationBookingSystem.dto.RequestDto.AddPersonRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.AddPersonResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.AgeGreaterThanResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.FemalesWhoTakenDose1ButNotDose2ResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.GetAllPersonFullyVaccinatedResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/addperson")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){
        try{
            AddPersonResponseDto personResponse = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(personResponse, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_email")
    public ResponseEntity updateEmail(@RequestParam("oldEmail") String oldEmail ,@RequestParam("newEmail") String newEmail){
        try {
            String response = personService.updateEmail(oldEmail, newEmail);
            return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-males-of-age-greater-than-a-certain-age")
    public ResponseEntity<List<AgeGreaterThanResponseDto>> getAllMalesGreaterThanNewAge(@RequestParam("newAge") int newAge){
        try{
            List<AgeGreaterThanResponseDto> ageGreaterThanResponseDto = personService.getAllMalesGreaterThanNewAge(newAge);
            return new ResponseEntity<>(ageGreaterThanResponseDto,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-females-who-have-taken-dose1-but-not-dose2")
    public ResponseEntity<List<FemalesWhoTakenDose1ButNotDose2ResponseDto>> getAllFemalesTakenDose1ButNotDose2(){
        try{
            List<FemalesWhoTakenDose1ButNotDose2ResponseDto> femalesWhoTakenDose1ButNotDose2ResponseDtos
                    = personService.getAllFemalesTakenDose1ButNotDose2();
            return new ResponseEntity<>(femalesWhoTakenDose1ButNotDose2ResponseDtos,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-person-who-is-fully-vaccinated")
    public ResponseEntity<List<GetAllPersonFullyVaccinatedResponseDto>> getAllFullyVaccinatedPerson(){
        try{
            List<GetAllPersonFullyVaccinatedResponseDto> allPersonFullyVaccinatedResponseDtos =
                    personService.getAllFullyVaccinatedPerson();

            return new ResponseEntity<>(allPersonFullyVaccinatedResponseDtos,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-the-people-who-have-not-taken-even-single-dose")
    public ResponseEntity<List<GetAllPersonFullyVaccinatedResponseDto>> getAllPeopleWhoHaveNotTakenEvenSingleDose(){
        //Here I am reusing GetAllPersonFullyVaccinatedResponseDto
        try{
            List<GetAllPersonFullyVaccinatedResponseDto> getAllPersonFullyVaccinatedResponseDtos =
                    personService.getAllPeopleWhoHaveNotTakenEvenSingleDose();
            return new ResponseEntity<>(getAllPersonFullyVaccinatedResponseDtos,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-females-whose-age-is-greater-than-particular-age-and-taken-only-Dose1")
    public ResponseEntity<List<GetAllPersonFullyVaccinatedResponseDto>>
            getAllFemalesWhoseAgeIsGreaterThanParticularAgeAndTakenDose1Only(@RequestParam("newAge")int newAge){
        //Here I am reusing GetAllPersonFullyVaccinatedResponseDto

        try{
            List<GetAllPersonFullyVaccinatedResponseDto> getAllPersonFullyVaccinatedResponseDtos =
                    personService.getAllFemalesWhoseAgeIsGreaterThanParticularAgeAndTakenDose1Only(newAge);
            return new ResponseEntity<>(getAllPersonFullyVaccinatedResponseDtos,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-males-whose-age-greater-than-given-age-taken-Both-Doses")
    public ResponseEntity<List<GetAllPersonFullyVaccinatedResponseDto>>
            getAllMalesAgeisGreaterThanGivenAgeAndTakenBothDoses(@RequestParam("newAge")int newAge){
        //Here I am reusing GetAllPersonFullyVaccinatedResponseDto
        try{
            List<GetAllPersonFullyVaccinatedResponseDto> getAllPersonFullyVaccinatedResponseDtos =
                    personService.getAllMalesAgeisGreaterThanGivenAgeAndTakenBothDoses(newAge);
            return new ResponseEntity<>(getAllPersonFullyVaccinatedResponseDtos,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    //DONE get all males of age greater than a certain age
    //DONE get all females who have taken only dose 1 NOT dose 2
    //Done get all the people who are fully vaccinated
    //Done get all the people who have not taken even single dose
    //Done get all females whose age is greater than a particular age and who have taken only dose 1
    //Done get all males whose age is greater than a particular age and who have taken both Doses
}
