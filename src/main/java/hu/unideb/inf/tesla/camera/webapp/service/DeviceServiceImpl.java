package hu.unideb.inf.tesla.camera.webapp.service;

import hu.unideb.inf.tesla.camera.webapp.model.Device;
import hu.unideb.inf.tesla.camera.webapp.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository repository;

    @Override
    public Optional<Device> getOne(String id) {
        return repository.findById(id);
    }

    @Override
    public Device createOne(DeviceRequest request) {
        OffsetDateTime dateTime = OffsetDateTime.now();
        Device device = Device.builder()
                .mac(request.getMac())
                .brand(request.getBrand())
                .device(request.getDevice())
                .model(request.getModel())
                .sdk(request.getSdk())
                .created(dateTime)
                .updated(dateTime)
                .build();
        return repository.insert(device);
    }

    @Override
    public Device updateOne(DeviceRequest request) {
        Device device = repository.findById(request.getMac())
                .orElseThrow(() -> new RuntimeException("shit"));

        device.setUpdated(OffsetDateTime.now());
        device.setBrand(request.getBrand());
        device.setDevice(request.getDevice());
        device.setModel(request.getModel());
        device.setSdk(request.getSdk());
        device.setName(request.getName());
        device.setFrequency(request.getFrequency());
        return repository.save(device);
    }

    @Override
    public List<Device> getListOfAll() {
        return repository.findAll();
    }
}
