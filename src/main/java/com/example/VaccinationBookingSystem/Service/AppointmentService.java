package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Model.Appointment;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repository.AppointmentRepository;
import com.example.VaccinationBookingSystem.Repository.DoctorRepository;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookAppointmentRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.BookAppointmentResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.GetAllAppointmentsOfParticularDoctorResponseDto;
import com.example.VaccinationBookingSystem.exception.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookAppointmentResponseDto bookAppointment(BookAppointmentRequestDto bookAppointmentRequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookAppointmentRequestDto.getPersonId());
        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("Invalid PersonId");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(bookAppointmentRequestDto.getDoctorId());
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Invalid DoctorId");
        }

        Person person = optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

        //create appointment object
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        doctor.getAppointmentList().add(savedAppointment);
        person.getAppointments().add(savedAppointment);

        Doctor savedDoctor = doctorRepository.save(doctor);
        Person savedPerson = personRepository.save(person);
        VaccinationCenter center = savedDoctor.getCenter();

        //send an email

        String text  = "Congratulations "+ savedPerson.getName() + " Your Appointment has been booked with Doctor "+
                savedDoctor.getName()+" Your Vaccination Center name is: "+ center.getCenterName() + " please reach at these address "+
                center.getAddress() + " at this time: " + savedAppointment.getAppointmentDate() + " Thank you";


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("darpanacciojob@gmail.com");
        simpleMailMessage.setTo(savedPerson.getEmailId());
        simpleMailMessage.setSubject("Congratulations !! You Successfully Booked your Appointment");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        //Prepare Response Dto
        //VaccinationCenter center = savedDoctor.getCenter();
        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterName(center.getCenterName());
        centerResponseDto.setCenterType(center.getCenterType());
        centerResponseDto.setAddress(center.getAddress());

        BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
        bookAppointmentResponseDto.setPersonName(savedPerson.getName());
        bookAppointmentResponseDto.setDoctorName(savedDoctor.getName());
        bookAppointmentResponseDto.setAppointmentId(appointment.getAppointmentId());
        bookAppointmentResponseDto.setAppointmentDate(appointment.getAppointmentDate());
        bookAppointmentResponseDto.setCenterResponseDto(centerResponseDto);

        return bookAppointmentResponseDto;

    }

//    public GetAllAppointmentsOfParticularDoctorResponseDto GetAllAppointmentOfParticularDoctor(Doctor doctor) {
//        List<Appointment> appointmentList = appointmentRepository.findAll();
//
//    }
}
