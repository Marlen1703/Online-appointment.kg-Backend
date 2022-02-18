package diplomabackend.service;


import diplomabackend.domain.Consumer;
import diplomabackend.repository.UserRepository;
import diplomabackend.service.UserImpl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Consumer> checkUser = userRepository.findUserByUsername(userName);
        if(checkUser.isEmpty()){
            throw new UsernameNotFoundException("User with this login not found");
        }else return new UserDetailsImpl(checkUser);
    }
}
