package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Image;
import edu.baylor.cs.beargo.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setContent(file.getBytes());
        return imageRepository.save(image);
    }

    public byte[] getImageById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()) {
            return image.get().getContent();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image exist for given id");
        }
    }
}