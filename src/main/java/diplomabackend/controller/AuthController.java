package diplomabackend.controller;

import diplomabackend.domain.Consumer;
import diplomabackend.dto.AuthRequestDTO;
import diplomabackend.dto.AuthResponseDTO;
import diplomabackend.dto.ConsumerDTO;
import diplomabackend.jwt.JwtTokenProvider;
import diplomabackend.repository.UserRepository;
import diplomabackend.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "consumer/api")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Autowired
    AuthService authService;


    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @PostMapping(value = "/auth")
    public AuthResponseDTO auth(@RequestBody AuthRequestDTO request) {
        Optional <Consumer> user = userRepository.findUserByUsername(request.getUsername());
        if(user!=null){
            if(passwordEncoder().matches(request.getPassword(), user.get().getPassword())){
                String token = jwtTokenProvider.createToken(user.get().getUsername());
                ConsumerDTO consumer=authService.auth(user.get());
                return new AuthResponseDTO(consumer,token);
            }
        }
        throw new UsernameNotFoundException("User doesn't exist");
    }
}
