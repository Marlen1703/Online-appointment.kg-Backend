package diplomabackend.controller;

import diplomabackend.service.MedicalCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    MedicalCardService medicalCardService;

    @GetMapping
    public void test(){
        System.out.println(
                medicalCardService.generatePersonalIdentifier()
        );
    }
}
