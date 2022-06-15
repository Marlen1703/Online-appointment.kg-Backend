package diplomabackend.controller;


import com.querydsl.core.types.Predicate;
import diplomabackend.domain.Appointment;
import diplomabackend.dto.AboutAppointmentDTO;
import diplomabackend.dto.AppointmentDTO;
import diplomabackend.dto.DiagnoseDTO;
import diplomabackend.dto.TodayAppointmentDTO;
import diplomabackend.jwt.JwtTokenProvider;
import diplomabackend.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin/api")
public class AdminController {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AppointmentService appointmentService;

    @GetMapping(value = "/appointment/today")
    public Page<TodayAppointmentDTO> getAllTodayAppointments(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestHeader("Authorization") String token){
        String login = jwtTokenProvider.getLoginFromToken(token.substring(7));
        return appointmentService.getAllTodayAppointments(page,size,login).map(TodayAppointmentDTO::new);
    };

    @GetMapping(value = "/appointment/recently")
    public Page<AppointmentDTO> getAllRecentlyAppointments(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestHeader("Authorization") String token,
                                                           @QuerydslPredicate(root = Appointment.class) Predicate predicate){
        String login = jwtTokenProvider.getLoginFromToken(token.substring(7));
        Page<Appointment> appointmentPage=appointmentService.getAllRecentlyAppointmentsByAdmin(page,size,login,predicate);
        List<AppointmentDTO> appointmentDTOS=appointmentPage.stream().map(element-> modelMapper.map(element,AppointmentDTO.class)).collect(Collectors.toList());
        Page<AppointmentDTO> result=new PageImpl<>(appointmentDTOS);
        return result;
    };

    @GetMapping(value = "/appointment/explored")
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

    @GetMapping(value = "/appointment/count")
    public int getCountOfAppointments(@QuerydslPredicate(root = Appointment.class) Predicate predicate,@RequestHeader("Authorization") String token){
        String login = jwtTokenProvider.getLoginFromToken(token.substring(7));
        return appointmentService.getAllAppointmentCount(login,predicate);
    }


    @GetMapping(value = "/appointment/about/{id}")
    public AboutAppointmentDTO getAboutAppointment(@PathVariable("id") Long id){
        return appointmentService.getAboutAppointment(id);
    }

    @PostMapping(value = "appointment/diagnose")
    public void diagnosePatient(@RequestBody DiagnoseDTO diagnose) {
        appointmentService.diagnosePatient(diagnose);
    }

}
