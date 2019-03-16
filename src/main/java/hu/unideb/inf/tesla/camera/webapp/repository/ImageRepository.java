package hu.unideb.inf.tesla.camera.webapp.repository;

import hu.unideb.inf.tesla.camera.webapp.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
}
