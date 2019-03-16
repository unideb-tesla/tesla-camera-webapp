package hu.unideb.inf.tesla.camera.webapp.web;

import hu.unideb.inf.tesla.camera.webapp.model.Image;
import hu.unideb.inf.tesla.camera.webapp.service.ImageService;
import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageControllerImpl implements ImageController {
    @Autowired
    private ImageService service;

    @PutMapping
    @Override
    public Image create(
            @RequestParam MultipartFile image,
            @RequestParam double timestamp,
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam String mac
    ) {
        return service.create(
                ImageService.CreateImageRequest.builder()
                        .image(image)
                        .timestamp(timestamp)
                        .longitude(longitude)
                        .latitude(latitude)
                        .mac(mac)
                        .build()
        );
    }

    @GetMapping("/{address}/id/{id}")
    @Override
    public ResponseEntity<byte[]> getImageByAddressAndId(
            @PathVariable String address,
            @PathVariable String id
    ) {
        Image image = service.getImageByAddressAndId(address, id);
        return ResponseEntity.ok()
                .header("Content-Type", image.getContentType())
                .body(Base64.decode(image.getContent()));
    }

    @GetMapping("/{address}/last")
    @ResponseBody
    @Override
    public ResponseEntity<byte[]> getLastImageByAddress(@PathVariable String address) {
        Image image = service.getLastImageByAddress(address);
        return ResponseEntity.ok()
                .header("Content-Type", image.getContentType())
                .body(Base64.decode(image.getContent()));
    }

    @GetMapping("/{address}/all")
    @Override
    public List<Image> getListByAddress(
            @PathVariable String address
    ) {
        return service.getListOfImagesByAddress(address);
    }
}
