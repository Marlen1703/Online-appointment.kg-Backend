package diplomabackend.controller;

import diplomabackend.domain.Appointment;
import diplomabackend.dto.NewAppointmentDTO;
import diplomabackend.repository.AppointmentRepository;
import diplomabackend.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;


    @Autowired
    AppointmentService appointmentService;


    @GetMapping("/{policy}")
    public Page getAllAppointmentsByPolicy(@PathVariable String policy, Pageable pageable){
         Page<Appointment> appointmentPage=appointmentRepository.findByPolicy(pageable,policy);
         return appointmentPage;
    }

    @PostMapping
    public void createNewAppointment(@RequestBody NewAppointmentDTO newAppointmentDTO){
        appointmentService.createNewAppointment(newAppointmentDTO);
    }
}
