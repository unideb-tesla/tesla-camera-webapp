package hu.unideb.inf.tesla.camera.webapp.web;

import hu.unideb.inf.tesla.camera.webapp.model.Device;
import hu.unideb.inf.tesla.camera.webapp.service.DeviceService;

import java.util.List;
import java.util.Optional;

public interface DeviceController {
    Optional<Device> getOne(String id);

    Device createOne(DeviceService.DeviceRequest request);

    Device updateOne(DeviceService.DeviceRequest request);

    List<Device> getAll();
}
