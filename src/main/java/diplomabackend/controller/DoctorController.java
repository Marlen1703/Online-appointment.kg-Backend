package diplomabackend.controller;

import diplomabackend.domain.Doctor;
import diplomabackend.dto.DoctorAuthRequestDTO;
import diplomabackend.dto.DoctorAuthResponseDTO;
import diplomabackend.dto.DoctorDTO;
import diplomabackend.jwt.JwtTokenProvider;
import diplomabackend.repository.DoctorRepository;
import diplomabackend.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/doctor/api")
public class DoctorController {

    @Autowired
    DoctorService doctorService;


    @Autowired
    DoctorRepository doctorRepository;


    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Autowired
    ModelMapper modelMapper;


    @GetMapping
    public Page<Doctor> getAllDoctors(Pageable pageable){
        Page <Doctor>doctorPage=doctorService.getAllDoctors(pageable);
        return doctorPage;
    }


    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @PostMapping(value = "/auth")
    public DoctorAuthResponseDTO auth(@RequestBody DoctorAuthRequestDTO request) {
        Optional<Doctor> doctor = doctorRepository.findByEmail(request.getEmail());
        if(doctor!=null){
            DoctorDTO doctorDTO=modelMapper.map(doctor.get(),DoctorDTO.class);
            if(passwordEncoder().matches(request.getPassword(), doctor.get().getPassword())){
                String token = jwtTokenProvider.createToken(doctor.get().getEmail());
                return new DoctorAuthResponseDTO(doctorDTO,token);
            }
        }
        throw new UsernameNotFoundException("Doctor doesn't exist");
    }
}
