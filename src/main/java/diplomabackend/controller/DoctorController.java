package diplomabackend.controller;

import diplomabackend.domain.Doctor;
import diplomabackend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;


    @GetMapping
    public Page<Doctor> getAllDoctors(Pageable pageable){
        Page <Doctor>doctorPage=doctorService.getAllDoctors(pageable);
        return doctorPage;
    }
}
