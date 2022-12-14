package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.Image;
import edu.baylor.cs.beargo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    ImageService imageService;

    /**
     * @param file the image file
     * @return the uploaded image
     * @throws IOException an IOException
     */
    @PostMapping("/upload")
    public Image uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }

    /**
     * @param id the image id
     * @return byte
     */
    @GetMapping(value = "download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] downloadImageById(@PathVariable Long id) {
        return imageService.getImageById(id);
    }
}