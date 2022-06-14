package diplomabackend.service;

import diplomabackend.domain.WorkHour;
import diplomabackend.dto.WorkHourDTO;
import diplomabackend.repository.WorkHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkHourService {

    @Autowired
    WorkHourRepository workHourRepository;


    public List<WorkHour> checkAvailableAppointments(String date,Long id) {
        List<WorkHour> workHours = workHourRepository.findAllByIdAndBookTime(date,id);
        return workHours;
    }
}
