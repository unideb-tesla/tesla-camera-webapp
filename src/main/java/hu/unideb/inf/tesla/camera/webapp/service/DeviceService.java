package hu.unideb.inf.tesla.camera.webapp.service;

import hu.unideb.inf.tesla.camera.webapp.model.Device;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    Optional<Device> getOne(String id);

    Device createOne(DeviceRequest request);

    Device updateOne(DeviceRequest request);

    List<Device> getListOfAll();

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class DeviceRequest {
        private String mac;
        private String brand;
        private String device;
        private String model;
        private String sdk;
        private String name;
        private int frequency;
    }
}
