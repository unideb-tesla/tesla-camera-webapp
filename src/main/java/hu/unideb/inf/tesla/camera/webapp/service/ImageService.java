package hu.unideb.inf.tesla.camera.webapp.service;

import hu.unideb.inf.tesla.camera.webapp.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image create(CreateImageRequest request);

    Image getLastImageByAddress(String address);

    Image getImageByAddressAndId(String address, String id);

    List<Image> getListOfImagesByAddress(String address);

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CreateImageRequest {
        private MultipartFile image;
        private double timestamp;
        private double longitude;
        private double latitude;
        private String mac;
    }
}
