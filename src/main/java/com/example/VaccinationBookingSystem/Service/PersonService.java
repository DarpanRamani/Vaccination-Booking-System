package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Enum.Gender;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.AddPersonRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.AddPersonResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.AgeGreaterThanResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.FemalesWhoTakenDose1ButNotDose2ResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.GetAllPersonFullyVaccinatedResponseDto;
import com.example.VaccinationBookingSystem.exception.PersonListIsEmptyException;
import com.example.VaccinationBookingSystem.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        //Convert Request Dto to Entity
        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setEmailId(addPersonRequestDto.getEmailId());
        person.setGender(addPersonRequestDto.getGender());
        person.setDose1Taken(false);
        person.setDose2Taken(false);
        person.setCertificate(null);

       Person savedPerson = personRepository.save(person);

       //Saved Entity -> Response dto
        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("congrats! you have been registered");
        return addPersonResponseDto;
    }

    public String updateEmail(String oldEmail, String newEmail) {
        Person person = personRepository.findByEmailId(oldEmail);
        if(person == null)
        {
            throw new PersonNotFoundException("Sorry Email does't exist");
        }
        person.setEmailId(newEmail);
        personRepository.save(person);
        return "congrats !! Your Email has been Updated Successfully";
    }

    public List<AgeGreaterThanResponseDto> getAllMalesGreaterThanNewAge(int newAge) {
        List<Person> personList= personRepository.findAll();

        if(personList.isEmpty()){
            throw new PersonListIsEmptyException("Person List is Empty");
        }
        List<AgeGreaterThanResponseDto> list = new ArrayList<>();
        for(Person person : personList){
            if(person.getAge() > newAge && person.getGender() == Gender.MALE){
                //Prepare the Resposne Dto
                AgeGreaterThanResponseDto ageGreaterThanResponseDto = new AgeGreaterThanResponseDto();
                ageGreaterThanResponseDto.setName(person.getName());
                ageGreaterThanResponseDto.setEmailId(person.getEmailId());

                list.add(ageGreaterThanResponseDto);
            }
        }
        return list;
    }

    public List<FemalesWhoTakenDose1ButNotDose2ResponseDto> getAllFemalesTakenDose1ButNotDose2() {
        List<Person> personList = personRepository.findAll();
        if(personList.isEmpty()){
            throw new PersonListIsEmptyException("Person List is Empty");
        }
        List<FemalesWhoTakenDose1ButNotDose2ResponseDto> list = new ArrayList<>();
        for(Person person : personList){
            if(person.isDose1Taken() && !person.isDose2Taken() && person.getGender() == Gender.FEMALE){
                FemalesWhoTakenDose1ButNotDose2ResponseDto femalesWhoTakenDose1ButNotDose2ResponseDto =
                        new FemalesWhoTakenDose1ButNotDose2ResponseDto();

                femalesWhoTakenDose1ButNotDose2ResponseDto.setName(person.getName());
                femalesWhoTakenDose1ButNotDose2ResponseDto.setAge(person.getAge());

                list.add(femalesWhoTakenDose1ButNotDose2ResponseDto);
            }
        }
        return list;
    }

    public List<GetAllPersonFullyVaccinatedResponseDto> getAllFullyVaccinatedPerson() {
        List<Person> personList = personRepository.findAll();
        if(personList.isEmpty()){
            throw new PersonListIsEmptyException("Person List is Empty");
        }
        List<GetAllPersonFullyVaccinatedResponseDto> list = new ArrayList<>();
        for(Person person : personList){
            if(person.isDose1Taken() && person.isDose2Taken()){
                GetAllPersonFullyVaccinatedResponseDto getAllPersonFullyVaccinatedResponseDto =
                        new GetAllPersonFullyVaccinatedResponseDto();

                getAllPersonFullyVaccinatedResponseDto.setName(person.getName());
                getAllPersonFullyVaccinatedResponseDto.setAge(person.getAge());
                getAllPersonFullyVaccinatedResponseDto.setEmailId(person.getEmailId());

                list.add(getAllPersonFullyVaccinatedResponseDto);
            }
        }
        return list;
    }

    public List<GetAllPersonFullyVaccinatedResponseDto> getAllPeopleWhoHaveNotTakenEvenSingleDose() {
        List<Person> personList = personRepository.findAll();
        if(personList.isEmpty()){
            throw new PersonListIsEmptyException("Person List is Empty");
        }
        List<GetAllPersonFullyVaccinatedResponseDto> list = new ArrayList<>();
        for(Person person : personList){
           if(!person.isDose1Taken() && !person.isDose2Taken()){
               GetAllPersonFullyVaccinatedResponseDto getAllPersonFullyVaccinatedResponseDto =
                       new GetAllPersonFullyVaccinatedResponseDto();

               getAllPersonFullyVaccinatedResponseDto.setName(person.getName());
               getAllPersonFullyVaccinatedResponseDto.setAge(person.getAge());
               getAllPersonFullyVaccinatedResponseDto.setEmailId(person.getEmailId());

               list.add(getAllPersonFullyVaccinatedResponseDto);
           }
        }
        return list;
    }

    public List<GetAllPersonFullyVaccinatedResponseDto>
    getAllFemalesWhoseAgeIsGreaterThanParticularAgeAndTakenDose1Only(int newAge) {
        List<Person> personList = personRepository.findAll();
        if(personList.isEmpty()){
            throw new PersonListIsEmptyException("Person List is Empty");
        }
        List<GetAllPersonFullyVaccinatedResponseDto> list = new ArrayList<>();
        for(Person person : personList){
            if(person.getGender() == Gender.FEMALE && person.isDose1Taken() && person.getAge() > newAge){
                GetAllPersonFullyVaccinatedResponseDto getAllPersonFullyVaccinatedResponseDto =
                        new GetAllPersonFullyVaccinatedResponseDto();

                getAllPersonFullyVaccinatedResponseDto.setName(person.getName());
                getAllPersonFullyVaccinatedResponseDto.setAge(person.getAge());
                getAllPersonFullyVaccinatedResponseDto.setEmailId(person.getEmailId());

                list.add(getAllPersonFullyVaccinatedResponseDto);
            }
        }
        return list;
    }


    public List<GetAllPersonFullyVaccinatedResponseDto> getAllMalesAgeisGreaterThanGivenAgeAndTakenBothDoses(int newAge) {
        List<Person> personList = personRepository.findAll();
        if(personList.isEmpty()){
            throw new PersonListIsEmptyException("Person List is Empty");
        }
        List<GetAllPersonFullyVaccinatedResponseDto> list = new ArrayList<>();
        for(Person person : personList){
            if(person.getGender() == Gender.MALE && person.isDose1Taken() && person.isDose2Taken() && person.getAge() > newAge){
                GetAllPersonFullyVaccinatedResponseDto getAllPersonFullyVaccinatedResponseDto =
                        new GetAllPersonFullyVaccinatedResponseDto();

                getAllPersonFullyVaccinatedResponseDto.setName(person.getName());
                getAllPersonFullyVaccinatedResponseDto.setAge(person.getAge());
                getAllPersonFullyVaccinatedResponseDto.setEmailId(person.getEmailId());

                list.add(getAllPersonFullyVaccinatedResponseDto);
            }
        }
        return list;
    }
}
