package diplomabackend.controller;

import diplomabackend.domain.Consumer;
import diplomabackend.domain.MedicalCard;
import diplomabackend.dto.PolicyDTO;
import diplomabackend.dto.RegistrationResponseDTO;
import diplomabackend.dto.RegistrationRequestDTO;
import diplomabackend.service.MedicalCardService;
import diplomabackend.service.StorageService;
import diplomabackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    StorageService storageService;

    @Autowired
    UserService userService;

    @Autowired
    MedicalCardService medicalCardService;

    @PostMapping(value = "/registration",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> createNewUser(@ModelAttribute RegistrationRequestDTO registrationRequestDTO){
        try{
            Consumer consumer = modelMapper.map(registrationRequestDTO, Consumer.class);
            String url=storageService.store(registrationRequestDTO.getAvatar(),consumer.getUsername());
            userService.createNewUser(consumer,url);
            medicalCardService.createMedicalCard(consumer);
            return ResponseEntity.ok("Success");
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
