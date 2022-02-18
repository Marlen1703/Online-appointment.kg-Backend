package diplomabackend.service;

import com.mifmif.common.regex.Generex;
import com.mifmif.common.regex.util.Iterator;
import diplomabackend.domain.MedicalCard;
import diplomabackend.domain.Consumer;
import diplomabackend.repository.MedicalCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalCardService {

    @Autowired
    MedicalCardRepository medicalCardRepository;

    public void createMedicalCard(Consumer consumer){
        MedicalCard medicalCard=new MedicalCard();
        medicalCard.setOwner(consumer);
        medicalCard.setPersonalIdentifier(generatePersonalIdentifier());
        medicalCardRepository.save(medicalCard);
    }

    public String generatePersonalIdentifier(){
        Generex generex = new Generex("[0-9]+");
        String result = generex.random(12,12);
        return result;
    }
}

