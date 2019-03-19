package hu.unideb.inf.tesla.camera.webapp.service;

import hu.unideb.inf.tesla.camera.webapp.model.Image;
import hu.unideb.inf.tesla.camera.webapp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository repository;
    private Image defaultImage;

    public ImageServiceImpl() {
        defaultImage = Image.builder()
                .content("PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDU4IDU4IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1OCA1ODsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSI1MTJweCIgaGVpZ2h0PSI1MTJweCI+CjxyZWN0IHg9IjEiIHk9IjciIHN0eWxlPSJmaWxsOiNDM0UxRUQ7c3Ryb2tlOiNFN0VDRUQ7c3Ryb2tlLXdpZHRoOjI7c3Ryb2tlLW1pdGVybGltaXQ6MTA7IiB3aWR0aD0iNTYiIGhlaWdodD0iNDQiLz4KPGNpcmNsZSBzdHlsZT0iZmlsbDojRUQ4QTE5OyIgY3g9IjE2IiBjeT0iMTcuNTY5IiByPSI2LjU2OSIvPgo8cG9seWdvbiBzdHlsZT0iZmlsbDojMUE5MTcyOyIgcG9pbnRzPSI1NiwzNi4xMTEgNTUsMzUgNDMsMjQgMzIuNSwzNS41IDM3Ljk4Myw0MC45ODMgNDIsNDUgNTYsNDUgIi8+Cjxwb2x5Z29uIHN0eWxlPSJmaWxsOiMxQTkxNzI7IiBwb2ludHM9IjIsNDkgMjYsNDkgMjEuOTgzLDQ0Ljk4MyAxMS4wMTcsMzQuMDE3IDIsNDEuOTU2ICIvPgo8cmVjdCB4PSIyIiB5PSI0NSIgc3R5bGU9ImZpbGw6IzZCNUI0QjsiIHdpZHRoPSI1NCIgaGVpZ2h0PSI1Ii8+Cjxwb2x5Z29uIHN0eWxlPSJmaWxsOiMyNUFFODg7IiBwb2ludHM9IjM3Ljk4Myw0MC45ODMgMjcuMDE3LDMwLjAxNyAxMCw0NSA0Miw0NSAiLz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPC9zdmc+Cg==")
                .contentType("image/svg+xml;utf8;base64")
                .build();
    }

    @Override
    public Image create(CreateImageRequest request) {
        try {
            String base64 = Base64.getEncoder().encodeToString(request.getImage().getBytes());
            Image image = Image.builder()
                    .content(base64)
                    .latitude(request.getLatitude())
                    .longitude(request.getLongitude())
                    .mac(request.getMac())
                    .name(request.getImage().getOriginalFilename())
                    .contentType(request.getImage().getContentType())
                    .latitude(request.getLatitude())
                    .created(OffsetDateTime.now())
                    .build();

            return repository.insert(image);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("shit");
        }
    }

    @Override
    public Image getLastImageByAddress(String address) {
        return repository.findAll().stream()
                .filter(image -> Objects.equals(image.getMac(), address))
                .sorted(Comparator.comparing(Image::getCreated, Comparator.reverseOrder()))
                .findFirst()
                .orElse(defaultImage);
    }

    @Override
    public Image getImageByAddressAndId(String address, String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Image> getListOfImagesByAddress(String address) {
        return repository.findAll().stream()
                .filter(image -> Objects.equals(image.getMac(), address))
                .peek(image -> image.setContent(null))
                .sorted(Comparator.comparing(Image::getCreated, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
