package diplomabackend.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import diplomabackend.StatusEnum;
import diplomabackend.domain.*;
import diplomabackend.domain.QAppointment;
import diplomabackend.dto.AboutAppointmentDTO;
import diplomabackend.dto.DiagnoseDTO;
import diplomabackend.dto.NewAppointmentDTO;
import diplomabackend.repository.AppointmentRepository;
import diplomabackend.repository.ConsumerRepository;
import diplomabackend.repository.DoctorRepository;
import diplomabackend.repository.WorkHourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {


    @Autowired
    AppointmentRepository appointmentRepository;


    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    WorkHourRepository workHourRepository;


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


    @Transactional
    public void createNewAppointment(NewAppointmentDTO newAppointmentDTO,String login) {

        Consumer consumer = consumerRepository.findByUsername(login);

        Doctor doctor=doctorRepository.getById(newAppointmentDTO.getDoctorId());

        Appointment appointment=modelMapper.map(newAppointmentDTO,Appointment.class);

        WorkHour workHour= WorkHour.builder()
                .bookTime(newAppointmentDTO.getTime())
                .doctorId(newAppointmentDTO.getDoctorId())
                .isAvailable(false)
                .build();
        workHourRepository.save(workHour);

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

    public int getAllAppointmentCount(String login, Predicate predicate) {
        final QAppointment qAppointment = QAppointment.appointment;
        final BooleanBuilder builder = new BooleanBuilder(predicate);
        builder.and(qAppointment.attendingDoctor.email.eq(login));
        List<Appointment> getAll = appointmentRepository.findAll(builder);
        return getAll.size();
    }


    public AboutAppointmentDTO getAboutAppointment(Long id) {
        Appointment appointment=appointmentRepository.findById(id).get();
        return new AboutAppointmentDTO(appointment);
    }

    @Transactional
    public void diagnosePatient(DiagnoseDTO diagnose) {
        Appointment appointment=appointmentRepository.findById(diagnose.getId()).get();
        appointment.setDiagnosis(diagnose.getDiagnosis());
        appointment.getConsumer().setPressure(diagnose.getPressure());
        appointment.getConsumer().setHeight(diagnose.getHeight());
        appointment.getConsumer().setTemperature(diagnose.getTemperature());
        appointment.getConsumer().setWeight(diagnose.getWeight());
        appointmentRepository.save(appointment);
    }
}


