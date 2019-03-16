package hu.unideb.inf.tesla.camera.webapp.repository;

import hu.unideb.inf.tesla.camera.webapp.model.Chain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChainRepository extends MongoRepository<Chain, String> {
}
