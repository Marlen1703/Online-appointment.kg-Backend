package diplomabackend.service;

import diplomabackend.domain.Consumer;
import diplomabackend.dto.AuthResponseDTO;
import diplomabackend.dto.ConsumerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    ModelMapper modelMapper;

    public ConsumerDTO auth(Consumer user){
        ConsumerDTO consumer = modelMapper.map(user,ConsumerDTO.class);
        return consumer;
    }
}
