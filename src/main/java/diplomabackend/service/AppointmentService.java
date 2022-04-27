package diplomabackend.service;

import diplomabackend.domain.Appointment;
import diplomabackend.domain.Doctor;
import diplomabackend.dto.NewAppointmentDTO;
import diplomabackend.exception.UserAlreadyExistException;
import diplomabackend.repository.AppointmentRepository;
import diplomabackend.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {


    @Autowired
    AppointmentRepository appointmentRepository;


    @Autowired
    DoctorRepository doctorRepository;


    @Autowired
    ModelMapper modelMapper;




    public void createNewAppointment(NewAppointmentDTO newAppointmentDTO) {

        Doctor doctor=doctorRepository.getById(newAppointmentDTO.getDoctorId());

        Appointment appointment=modelMapper.map(newAppointmentDTO,Appointment.class);

        appointment.setAttendingDoctor(doctor);

        appointmentRepository.save(appointment);

    }

}


