package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repository.DoctorRepository;
import com.example.VaccinationBookingSystem.Repository.VaccinationCenterRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.CenterRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.*;
import com.example.VaccinationBookingSystem.exception.DoctorListIsEmptyException;
import com.example.VaccinationBookingSystem.exception.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.exception.VaccinationCenterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaccinationCenterService {

    @Autowired
    VaccinationCenterRepository centerRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        //request Dto -> Entity
        VaccinationCenter center = new VaccinationCenter();
        center.setCenterName(centerRequestDto.getCenterName());
        center.setCenterType(centerRequestDto.getCenterType());
        center.setAddress(centerRequestDto.getAddress());

       VaccinationCenter savedCenter = centerRepository.save(center);

        //Entity -> response Dto
        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setAddress(savedCenter.getAddress());
        return centerResponseDto;
    }

    public List<DoctorResponseDto> getAllDoctorsOfParticularCenterType(CenterType centerType) {
       List<Doctor> doctorList = doctorRepository.findAll();
       if(doctorList.isEmpty()){
           throw new DoctorListIsEmptyException("Doctor List is Empty");
       }
       List<DoctorResponseDto> list = new ArrayList<>();
       for(Doctor doctor : doctorList){
           if(doctor.getCenter().getCenterType().equals(centerType)){
               DoctorResponseDto doctorResponseDto = new DoctorResponseDto();

               doctorResponseDto.setName(doctor.getName());
               doctorResponseDto.setMessage("hi!!");

               VaccinationCenter vaccinationCenter = doctor.getCenter();

               CenterResponseDto centerResponseDto = new CenterResponseDto();
               centerResponseDto.setCenterName(vaccinationCenter.getCenterName());
               centerResponseDto.setCenterType(vaccinationCenter.getCenterType());
               centerResponseDto.setAddress(vaccinationCenter.getAddress());

               doctorResponseDto.setCenterResponseDto(centerResponseDto);

               list.add(doctorResponseDto);
           }
       }
       return list;
    }

    public CenterWithHighestNumberOfDoctorsAppointmentResponseDto GetTheCenterWithHighestNumberOfDoctors() {
        List<VaccinationCenter> vaccinationCenterList = centerRepository.findAll();
        if(vaccinationCenterList.isEmpty()){
            throw new VaccinationCenterNotFoundException("NO Such Vacciation Center Found");
        }
        int highest = 0;
        CenterWithHighestNumberOfDoctorsAppointmentResponseDto center = new CenterWithHighestNumberOfDoctorsAppointmentResponseDto();

        for(VaccinationCenter vaccinationCenter : vaccinationCenterList){
            if(vaccinationCenter.getDoctors().size() > highest){
                highest = vaccinationCenter.getDoctors().size();

                center.setCenterName(vaccinationCenter.getCenterName());
                center.setCenterType(vaccinationCenter.getCenterType());
                center.setAddress(vaccinationCenter.getAddress());
                center.setHighestDoctors(highest);
            }
        }
        return center;

    }

//    public int getTheCenterWithHighestNumberOfDoctorsAmongParticularCenterType() {
//        return centerRepository.getTheCenterWithHighestNumberOfDoctorsAmongParticularCenterType();
//    }
}
