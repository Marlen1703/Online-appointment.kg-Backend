package diplomabackend.service;

import diplomabackend.domain.MedicalCard;
import diplomabackend.domain.Consumer;
import diplomabackend.repository.MedicalCardRepository;
import diplomabackend.repository.UserRepository;
import diplomabackend.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MedicalCardService {

    @Autowired
    MedicalCardRepository medicalCardRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    Generator generator;

    public long createMedicalCard(Consumer consumer){
        MedicalCard medicalCard=new MedicalCard();
        medicalCard.setOwner(consumer);
        long personalIdentifier=generator.generatePersonalIdentifier();
        medicalCard.setPolicy(String.valueOf(personalIdentifier));

        consumer.setMedicalCard(medicalCard);
        userRepository.save(consumer);
        medicalCardRepository.save(medicalCard);
        return personalIdentifier;
    }



}

