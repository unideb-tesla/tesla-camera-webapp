package hu.unideb.inf.tesla.camera.webapp.web;

import hu.unideb.inf.tesla.camera.webapp.model.Chain;
import hu.unideb.inf.tesla.camera.webapp.service.ChainService;

import java.util.List;
import java.util.Optional;

public interface ChainController {
    Chain createOne(ChainService.ChainRequest request);

    Chain updateOne(ChainService.ChainRequest request);

    Optional<Chain> getActiveOne();

    void setActiveOne(String id);

    void removeActiveOne();

    void deleteOne(String id);

    Optional<Chain> getOne(String id);

    List<Chain> getAll();
}
