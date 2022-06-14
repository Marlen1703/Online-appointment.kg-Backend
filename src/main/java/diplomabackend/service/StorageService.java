package diplomabackend.service;

import diplomabackend.domain.Consumer;
import diplomabackend.jwt.JwtTokenProvider;
import diplomabackend.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@Service
public class StorageService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;



    private final String uploadDir = "/getFile/avatar/";

    public String store(MultipartFile file, String login) throws IOException {
        UUID uuid = UUID.randomUUID();
        String uuidWithExtension = uuid + getExtensionByStringHandling(file.getOriginalFilename());
        FileOutputStream output = new FileOutputStream(new File("src/main/getFile/avatar")
                .getAbsolutePath() + "/" + uuidWithExtension);
        output.write(file.getBytes());
        output.close();
        return uploadDir + uuidWithExtension;
    }


    public String getExtensionByStringHandling(String filename) {
        String extractedExtension = Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
        return new String("." + extractedExtension);
    }

}