package diplomabackend.service;

import diplomabackend.domain.Consumer;
import diplomabackend.domain.Doctor;
import diplomabackend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Page<Doctor> getAllDoctors(Pageable pageable){

        Page<Doctor> doctorPage=doctorRepository.findAll(pageable);
        return doctorPage;
    }

    public void createNewDoctor(Doctor doctor) throws Exception {
            Optional<Doctor> userExist=doctorRepository.findByEmail(doctor.getEmail());
            if(userExist.isPresent()){
                throw new Exception();
            }
            String jwtPassword= passwordEncoder.encode(doctor.getPassword());
            doctor.setPassword(jwtPassword);
            doctorRepository.save(doctor);
    }
}
