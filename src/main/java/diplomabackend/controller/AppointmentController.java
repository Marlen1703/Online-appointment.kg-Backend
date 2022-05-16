package diplomabackend.controller;

import diplomabackend.domain.Appointment;
import diplomabackend.dto.AppointmentDTO;
import diplomabackend.dto.NewAppointmentDTO;
import diplomabackend.repository.AppointmentRepository;
import diplomabackend.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;


    @Autowired
    AppointmentService appointmentService;



    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/policy/{policy}")
    public Page getAllAppointmentsByPolicy(@PathVariable String policy, Pageable pageable){
         Page<Appointment> appointmentPage=appointmentRepository.findByPolicy(pageable,policy);
         return appointmentPage;
    }

    @PostMapping
    public void createNewAppointment(@RequestBody NewAppointmentDTO newAppointmentDTO){
        appointmentService.createNewAppointment(newAppointmentDTO);
    }

    @GetMapping(value = "/id/{id}")
    public AppointmentDTO getOneAppointmentById(@PathVariable Long id){
        AppointmentDTO appointmentDTO=modelMapper.map(appointmentService.getOneAppointmentById(id),AppointmentDTO.class);
        return appointmentDTO;
    }


    @GetMapping
    public List<AppointmentDTO> getAllAppointments(Pageable pageable){
        List<Appointment> appointmentPage= appointmentService.getAllAppointments(pageable);
        List<AppointmentDTO> appointmentDTOS=appointmentPage.stream().map(appointment -> modelMapper
                        .map(appointment,AppointmentDTO.class))
                .collect(Collectors.toList());
        return appointmentDTOS;
    }
}
