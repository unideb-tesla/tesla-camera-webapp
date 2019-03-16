package hu.unideb.inf.tesla.camera.webapp.web;

import hu.unideb.inf.tesla.camera.webapp.model.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageController {
    Image create(
            MultipartFile image,
            double timestamp,
            double longitude,
            double latitude,
            String mac
    );

    List<Image> getListByAddress(String address);

    ResponseEntity<byte[]> getImageByAddressAndId(String address, String id);

    ResponseEntity<byte[]> getLastImageByAddress(String address);
}
