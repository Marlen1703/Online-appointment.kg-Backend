package diplomabackend.controller;

import diplomabackend.domain.WorkHour;
import diplomabackend.dto.WorkHourDTO;
import diplomabackend.service.WorkHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/work-hour")
public class WorkHourController {

    @Autowired
    WorkHourService workHourService;

    @GetMapping(value = "/check")
    public List<WorkHourDTO> checkAvailableAppointments(@RequestParam String date,@RequestParam Long id){
        List <WorkHourDTO> workHourDTOS=workHourService.checkAvailableAppointments(date,id).stream().map(WorkHourDTO::new).collect(Collectors.toList());
        return workHourDTOS;
    }
}
