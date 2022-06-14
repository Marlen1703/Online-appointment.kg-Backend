package diplomabackend.service;

import diplomabackend.domain.Consumer;
import diplomabackend.email.MailSender;
import diplomabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;




    public Consumer createNewUser(Consumer consumer,String url) throws Exception {
        Optional<Consumer> userExist=userRepository.findUserByUsername(consumer.getUsername());
        if(userExist.isPresent()){
            throw new Exception();
        }
        String jwtPassword= passwordEncoder.encode(consumer.getPassword());
        consumer.setPassword(jwtPassword);
        consumer.setAvatar(url);
        userRepository.save(consumer);
        return consumer;
    }
}
