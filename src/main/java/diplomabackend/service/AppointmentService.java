package diplomabackend.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import diplomabackend.StatusEnum;
import diplomabackend.domain.Appointment;
import diplomabackend.domain.Consumer;
import diplomabackend.domain.Doctor;
import diplomabackend.domain.QAppointment;
import diplomabackend.dto.AppointmentDTO;
import diplomabackend.dto.NewAppointmentDTO;
import diplomabackend.exception.UserAlreadyExistException;
import diplomabackend.repository.AppointmentRepository;
import diplomabackend.repository.ConsumerRepository;
import diplomabackend.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {


    @Autowired
    AppointmentRepository appointmentRepository;


    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ConsumerRepository consumerRepository;


    @Autowired
    ModelMapper modelMapper;


    public List<Appointment> getAllAppointments(Pageable pageable){
        List<Appointment> appointmentPage=appointmentRepository.findAll();
        return appointmentPage;
    }

    public Appointment getOneAppointmentById(Long id) {
        Optional <Appointment> appointment=appointmentRepository.findById(id);
        return appointment.get();
    }

    public void createNewAppointment(NewAppointmentDTO newAppointmentDTO,String login) {

        Consumer consumer = consumerRepository.findByUsername(login);

        Doctor doctor=doctorRepository.getById(newAppointmentDTO.getDoctorId());

        Appointment appointment=modelMapper.map(newAppointmentDTO,Appointment.class);

        appointment.setAttendingDoctor(doctor);
        appointment.setConsumer(consumer);
        appointment.setStatus(StatusEnum.UNEXPLORED);
        appointmentRepository.save(appointment);

    }

    public Page<Appointment> getAllTodayAppointments(int page, int size, String login) {
        Doctor doctor=doctorRepository.findByEmail(login).get();
        Pageable pageable= PageRequest.of(page,size);
        Page <Appointment> appointmentPage=appointmentRepository.findAllTodayAppointments(doctor.getId(),pageable);
        return appointmentPage;
    }

    public Page<Appointment> getAllRecentlyAppointments(int page, int size, String login, Predicate predicate) {
        final QAppointment qAppointment = QAppointment.appointment;
        final BooleanBuilder builder = new BooleanBuilder(predicate);
        builder.and(qAppointment.attendingDoctor.email.eq(login).and(qAppointment.status.eq(StatusEnum.UNEXPLORED)));
        Pageable pageable= PageRequest.of(page,size);
        Page <Appointment> appointmentPage=appointmentRepository.findAll(builder.getValue(),pageable);
        return appointmentPage;
    }


    public Page<Appointment> getAllAppointments(int page, int size, String login, Predicate predicate) {
        final QAppointment qAppointment = QAppointment.appointment;
        final BooleanBuilder builder = new BooleanBuilder(predicate);
        builder.and(qAppointment.attendingDoctor.email.eq(login).and(qAppointment.status.eq(StatusEnum.EXPLORED)));
        Pageable pageable = PageRequest.of(page,size);
        Page <Appointment> appointmentPage=appointmentRepository.findAll(builder.getValue(),pageable);
        return appointmentPage;
    }


}


