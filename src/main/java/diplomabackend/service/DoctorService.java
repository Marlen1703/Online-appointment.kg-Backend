package diplomabackend.service;

import diplomabackend.domain.Doctor;
import diplomabackend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Page<Doctor> getAllDoctors(Pageable pageable){

        Page<Doctor> doctorPage=doctorRepository.findAll(pageable);
        return doctorPage;
    }
}
