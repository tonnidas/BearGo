package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Image;
import edu.baylor.cs.beargo.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ImageServiceTest {

    @Autowired
    ImageService imageService;

    @MockBean
    ImageRepository imageRepository;

    @MockBean
    MultipartFile multipartFile;

    private Image getImage() {
        Image image = null;
        return image;
    }

    @Test
    void uploadImage() throws IOException {
        Image image = getImage();
        when(imageRepository.save(image)).thenReturn(image);
        assertEquals(image, imageService.uploadImage(multipartFile));
    }
}
