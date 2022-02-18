package diplomabackend.controller;

import diplomabackend.domain.Consumer;
import diplomabackend.dto.RegistrationResponseDTO;
import diplomabackend.dto.RegistrationRequestDTO;
import diplomabackend.service.MedicalCardService;
import diplomabackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @Autowired
    MedicalCardService medicalCardService;

    @PostMapping(value = "/registration")
    public ResponseEntity<RegistrationResponseDTO> createNewUser(@RequestBody RegistrationRequestDTO registrationRequestDTO){
        try{
            Consumer consumer =modelMapper.map(registrationRequestDTO, Consumer.class);
            userService.createNewUser(consumer);
            medicalCardService.createMedicalCard(consumer);
            return ResponseEntity.ok(modelMapper.map(consumer, RegistrationResponseDTO.class));
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
