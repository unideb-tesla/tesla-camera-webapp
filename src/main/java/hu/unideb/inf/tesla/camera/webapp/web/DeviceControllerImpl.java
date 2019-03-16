package hu.unideb.inf.tesla.camera.webapp.web;

import hu.unideb.inf.tesla.camera.webapp.model.Device;
import hu.unideb.inf.tesla.camera.webapp.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/device")
public class DeviceControllerImpl implements DeviceController {
    @Autowired
    private DeviceService service;

    @GetMapping("/{mac}")
    @Override
    public Optional<Device> getOne(@PathVariable String mac) {
        return service.getOne(mac);
    }

    @GetMapping
    @Override
    public List<Device> getAll() {
        return service.getListOfAll();
    }

    @PutMapping
    @Override
    public Device createOne(@RequestBody DeviceService.DeviceRequest request) {
        return service.createOne(request);
    }

    @PostMapping
    @Override
    public Device updateOne(@RequestBody DeviceService.DeviceRequest request) {
        return service.updateOne(request);
    }
}
