package diplomabackend.controller;

import diplomabackend.domain.Consumer;
import diplomabackend.domain.Doctor;
import diplomabackend.dto.*;
import diplomabackend.jwt.JwtTokenProvider;
import diplomabackend.repository.DoctorRepository;
import diplomabackend.service.DoctorService;
import diplomabackend.service.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;


    @Autowired
    DoctorRepository doctorRepository;


    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StorageService storageService;


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

    @PostMapping(value = "/registration",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> createNewDoctor(@ModelAttribute DoctorRegistrationDTO doctorRegistrationDTO){
        try{
            Doctor doctor = modelMapper.map(doctorRegistrationDTO, Doctor.class);
            String url=storageService.store(doctorRegistrationDTO.getAvatar(),doctor.getEmail());
            doctorService.createNewDoctor(doctor,url);

            return ResponseEntity.ok("Success");
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
