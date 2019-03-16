package hu.unideb.inf.tesla.camera.webapp.repository;

import hu.unideb.inf.tesla.camera.webapp.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepository extends MongoRepository<Device, String> {
}
