package diplomabackend.controller;

import com.querydsl.core.types.Predicate;
import diplomabackend.domain.Appointment;
import diplomabackend.dto.AppointmentDTO;
import diplomabackend.dto.NewAppointmentDTO;
import diplomabackend.dto.TodayAppointmentDTO;
import diplomabackend.jwt.JwtTokenProvider;
import diplomabackend.repository.AppointmentRepository;
import diplomabackend.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;


    @Autowired
    AppointmentService appointmentService;



    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @GetMapping("/policy/{policy}")
    public Page getAllAppointmentsByPolicy(@PathVariable String policy, Pageable pageable){
        
         Page<Appointment> appointmentPage=appointmentRepository.findByPolicy(pageable,policy);
         return appointmentPage;
    }


    @PostMapping
    public void createNewAppointment(@RequestBody NewAppointmentDTO newAppointmentDTO,
                                     @RequestHeader("Authorization") String token
                                     ){
        String login = jwtTokenProvider.getLoginFromToken(token.substring(7));
        appointmentService.createNewAppointment(newAppointmentDTO,login);
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

    @GetMapping(value = "/admin/today")
    public Page<TodayAppointmentDTO> getAllTodayAppointments(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestHeader("Authorization") String token){
        String login = jwtTokenProvider.getLoginFromToken(token.substring(7));
       return appointmentService.getAllTodayAppointments(page,size,login).map(TodayAppointmentDTO::new);
    };

    @GetMapping(value = "/admin/recently")
    public Page<AppointmentDTO> getAllRecentlyAppointments(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestHeader("Authorization") String token,
                                                           @QuerydslPredicate(root = Appointment.class) Predicate predicate){
        String login = jwtTokenProvider.getLoginFromToken(token.substring(7));
        Page<Appointment> appointmentPage=appointmentService.getAllRecentlyAppointments(page,size,login,predicate);
        List <AppointmentDTO> appointmentDTOS=appointmentPage.stream().map(element-> modelMapper.map(element,AppointmentDTO.class)).collect(Collectors.toList());
        Page<AppointmentDTO> result=new PageImpl<>(appointmentDTOS);
        return result;
    };

    @GetMapping(value = "/admin/explored")
    public Page<AppointmentDTO> getAllAppointments(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestHeader("Authorization") String token,
                                                           @QuerydslPredicate(root = Appointment.class) Predicate predicate){
        String login = jwtTokenProvider.getLoginFromToken(token.substring(7));
        Page<Appointment> appointmentPage=appointmentService.getAllAppointments(page,size,login,predicate);
        List <AppointmentDTO> appointmentDTOS=appointmentPage.stream().map(element-> modelMapper.map(element,AppointmentDTO.class)).collect(Collectors.toList());
        Page<AppointmentDTO> result=new PageImpl<>(appointmentDTOS);
        return result;
    };

    @GetMapping(value = "/admin/count")
    public int getCountOfAppointments(@QuerydslPredicate(root = Appointment.class) Predicate predicate,@RequestHeader("Authorization") String token){
        String login = jwtTokenProvider.getLoginFromToken(token.substring(7));
        return appointmentService.getAllAppointmentCount(login,predicate);
    }






}
