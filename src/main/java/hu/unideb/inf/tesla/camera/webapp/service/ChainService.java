package hu.unideb.inf.tesla.camera.webapp.service;

import hu.unideb.inf.tesla.camera.webapp.model.Chain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

public interface ChainService {
    Chain createOne(ChainRequest request);

    Chain updateOne(ChainRequest request);

    Optional<Chain> getActiveOne();

    void setActiveOne(String id);

    void removeActiveOne();

    void deleteOne(String id);

    Optional<Chain> getOne(String id);

    List<Chain> getAll();


    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ChainRequest {
        private String id;
        private String name;
        private int length;
        private int intervalDuration;
        private int delay;
    }
}
