package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repository.DoctorRepository;
import com.example.VaccinationBookingSystem.Repository.VaccinationCenterRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.CenterRequestDto;
import com.example.VaccinationBookingSystem.dto.RequestDto.DoctorRequestDto;
import com.example.VaccinationBookingSystem.dto.RequestDto.UpdateDoctorEmailOrAgeRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.DoctorResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.DoctorWhoBelongsToParticularGivenCenterResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.DoctorWithHighestAppoinmentResponseDto;
import com.example.VaccinationBookingSystem.exception.CenterNotFoundException;
import com.example.VaccinationBookingSystem.exception.DoctorListIsEmptyException;
import com.example.VaccinationBookingSystem.exception.DoctorNotFoundException;
import org.hibernate.mapping.BasicValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    VaccinationCenterRepository centerRepository;

    @Autowired
    DoctorRepository doctorRepository;
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {
        Optional<VaccinationCenter> optionalVaccinationCenter = centerRepository.findById(doctorRequestDto.getCenterId());
        if(optionalVaccinationCenter.isEmpty()){
            throw new CenterNotFoundException("sorry! wrong center Id");
        }
        VaccinationCenter center = optionalVaccinationCenter.get();

        //create doctor Entity
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequestDto.getName());
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setEmailId(doctorRequestDto.getEmailId());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setCenter(center);

        //add in center's doctor list
        center.getDoctors().add(doctor);

       VaccinationCenter savedCenter = centerRepository.save(center);  //These will save both center and doctor because center is parent and doctor is child
                                                                        // if we save parent child will automatically save.

        //Prepare Response Dto
        List<Doctor> doctors = savedCenter.getDoctors();
        Doctor latestSavedDoctors = doctors.get(doctors.size() - 1);

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setAddress(savedCenter.getAddress());

        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setName(latestSavedDoctors.getName());
        doctorResponseDto.setMessage("Congrats! you have been Registered");
        doctorResponseDto.setCenterResponseDto(centerResponseDto);

        return doctorResponseDto;
    }

    public List<String> getAgeGreaterThan(int newAge) {
        List<Doctor> doctors = doctorRepository.getAgeGreaterThan(newAge);
        List<String> ans = new ArrayList<>();

        for(Doctor d : doctors)
            ans.add(d.getName());

        return ans;
    }


    public DoctorWithHighestAppoinmentResponseDto doctorWithHighestNumberOfAppointments() {
        List<Doctor> doctorList = doctorRepository.findAll();
        if(doctorList.isEmpty()){
            throw new DoctorNotFoundException("Doctor List is Empty");
        }
        int highest = 0;
        DoctorWithHighestAppoinmentResponseDto doctorWithHighestAppoinmentResponseDto =
                new DoctorWithHighestAppoinmentResponseDto();
        for(Doctor doctor : doctorList){
            if(doctor.getAppointmentList().size() > highest){
                highest = doctor.getAppointmentList().size();

                doctorWithHighestAppoinmentResponseDto.setName(doctor.getName());
                doctorWithHighestAppoinmentResponseDto.setAge(doctor.getAge());
                doctorWithHighestAppoinmentResponseDto.setEmailId(doctor.getEmailId());
                doctorWithHighestAppoinmentResponseDto.setHighestAppointment(highest);
            }
        }
        return doctorWithHighestAppoinmentResponseDto;
    }

    //MADE EXTRA API NOT TESTED
    public List<DoctorWhoBelongsToParticularGivenCenterResponseDto>
             listOfDoctorsWhoBelongToParticularCenter(CenterRequestDto centerRequestDto) {
        List<Doctor> doctorList = doctorRepository.findAll();
        if(doctorList.isEmpty()){
            throw new DoctorListIsEmptyException("Doctor List is Empty");
        }
        List<DoctorWhoBelongsToParticularGivenCenterResponseDto> list = new ArrayList<>();
        for(Doctor doctor : doctorList){

            VaccinationCenter center = doctor.getCenter();

            if(center.getCenterType() == centerRequestDto.getCenterType() &&
                    center.getCenterName().equals(centerRequestDto.getCenterName()) &&
                    center.getAddress().equals(centerRequestDto.getAddress())){

                //prepare response
                DoctorWhoBelongsToParticularGivenCenterResponseDto doctorWhoBelongsToParticularGivenCenterResponseDto =
                        new DoctorWhoBelongsToParticularGivenCenterResponseDto();
                doctorWhoBelongsToParticularGivenCenterResponseDto.setName(doctor.getName());
                doctorWhoBelongsToParticularGivenCenterResponseDto.setAge(doctor.getAge());
                doctorWhoBelongsToParticularGivenCenterResponseDto.setEmailId(doctor.getEmailId());
                doctorWhoBelongsToParticularGivenCenterResponseDto.setGender(doctor.getGender());

                VaccinationCenter vaccinationCenter = doctor.getCenter();

                CenterResponseDto centerResponseDto = new CenterResponseDto();
                centerResponseDto.setCenterName(center.getCenterName());
                centerResponseDto.setCenterType(center.getCenterType());
                centerResponseDto.setAddress(center.getAddress());

                doctorWhoBelongsToParticularGivenCenterResponseDto.setCenterResponseDto(centerResponseDto);

                list.add(doctorWhoBelongsToParticularGivenCenterResponseDto);
            }
        }
        if(list.isEmpty()){
            throw new DoctorListIsEmptyException("No Doctor Found to This Center");
        }
        return list;
    }

    public List<DoctorWhoBelongsToParticularGivenCenterResponseDto>
    listOfDoctorWhoBelongToParticularGivenCenterType(CenterType centerType) {

        List<Doctor> doctorList = doctorRepository.findAll();
        if(doctorList.isEmpty()){
            throw new DoctorListIsEmptyException("Doctor List is Empty");
        }

        List<DoctorWhoBelongsToParticularGivenCenterResponseDto> list = new ArrayList<>();
        for(Doctor doctor : doctorList){
            CenterType centerType1 = doctor.getCenter().getCenterType();

            if(centerType1.equals(centerType)){
                DoctorWhoBelongsToParticularGivenCenterResponseDto doctorResponse =
                        new DoctorWhoBelongsToParticularGivenCenterResponseDto();

                doctorResponse.setName(doctor.getName());
                doctorResponse.setAge(doctor.getAge());
                doctorResponse.setGender(doctor.getGender());
                doctorResponse.setEmailId(doctor.getEmailId());

                VaccinationCenter vaccinationCenter = doctor.getCenter();

                CenterResponseDto centerResponseDto = new CenterResponseDto();

                centerResponseDto.setAddress(vaccinationCenter.getAddress());
                centerResponseDto.setCenterName(vaccinationCenter.getCenterName());
                centerResponseDto.setCenterType(vaccinationCenter.getCenterType());

                doctorResponse.setCenterResponseDto(centerResponseDto);

                list.add(doctorResponse);
            }
        }
        return list;
    }

    public String updateEmailAndOrAgeOfDoctor(UpdateDoctorEmailOrAgeRequestDto updateDoctorEmailOrAgeRequestDto) {
        //Without using findAll function Making custom function in doctorRepo These is the 2nd type we can search
        Doctor doctor = doctorRepository.findByEmailId(updateDoctorEmailOrAgeRequestDto.getOldEmailId());
        if(doctor == null){
            throw new DoctorNotFoundException("Doctor is Not Found");
        }

        if(updateDoctorEmailOrAgeRequestDto.getNewAge() != 0 && updateDoctorEmailOrAgeRequestDto.getNewEmailId() != null)
        {
            doctor.setEmailId(updateDoctorEmailOrAgeRequestDto.getNewEmailId());
            doctor.setAge(updateDoctorEmailOrAgeRequestDto.getNewAge());
        }
        else if(updateDoctorEmailOrAgeRequestDto.getNewAge() != 0){
            doctor.setAge(updateDoctorEmailOrAgeRequestDto.getNewAge());
        }
        else if(updateDoctorEmailOrAgeRequestDto.getNewEmailId() != null){
            doctor.setEmailId(updateDoctorEmailOrAgeRequestDto.getNewEmailId());
        }

        doctorRepository.save(doctor);

        return "Age and EmailId Updated SuccessFully";
    }
}