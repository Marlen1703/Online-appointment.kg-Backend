package diplomabackend.service;

import diplomabackend.domain.Appointment;
import diplomabackend.domain.WorkHour;
import diplomabackend.dto.WorkHourDTO;
import diplomabackend.repository.AppointmentRepository;
import diplomabackend.repository.WorkHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkHourService {

    @Autowired
    WorkHourRepository workHourRepository;

    @Autowired
    AppointmentRepository appointmentRepository;


    public List<WorkHourDTO> checkAvailableAppointments(String date,Long id) {
        List<Appointment> appointmentList=appointmentRepository.findAllByIdAndBookTime(date,id);
        List<WorkHourDTO> workHours = workHourRepository.findAll().stream().map(WorkHourDTO::new).collect(Collectors.toList());

        workHours.forEach(workHourDTO -> workHourDTO.setWorkHour(date+"T"+workHourDTO.getWorkHour()));

        for (Appointment appointment:appointmentList) {
            workHours.forEach(workHourDTO -> {
                if(workHourDTO.getWorkHour()
                        .equals(appointment.getTime().toString())){
                    workHourDTO.setAvailable(false);
                }
            });
        }
        return workHours;
    }
}
