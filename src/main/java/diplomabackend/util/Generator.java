package diplomabackend.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Generator {

    public long generatePersonalIdentifier(){
        Random random = new Random();
        char[] digits = new char[12];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < 12; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }
}
