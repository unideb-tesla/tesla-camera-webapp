package hu.unideb.inf.tesla.camera.webapp.service;

import hu.unideb.inf.tesla.camera.webapp.model.Chain;
import hu.unideb.inf.tesla.camera.webapp.repository.ChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChainServiceImpl implements ChainService {
    private Optional<String> activeId;

    @Autowired
    private ChainRepository repository;


    public ChainServiceImpl() {
        activeId = Optional.empty();
    }

    @Override
    public Chain createOne(ChainRequest request) {
        OffsetDateTime dateTime = OffsetDateTime.now();
        Chain chain = Chain.builder()
                .name(request.getName())
                .delay(request.getDelay())
                .intervalDuration(request.getIntervalDuration())
                .length(request.getLength())
                .created(dateTime)
                .updated(dateTime)
                .build();

        return repository.insert(chain);
    }

    @Override
    public void deleteOne(String id) {
        if (activeId.isPresent() && Objects.equals(activeId.get(), id)) {
            activeId = Optional.empty();
        }

        repository.deleteById(id);
    }

    @Override
    public Chain updateOne(ChainRequest request) {
        Chain chain = repository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("shit"));

        chain.setName(request.getName());
        chain.setDelay(request.getDelay());
        chain.setIntervalDuration(request.getIntervalDuration());
        chain.setLength(request.getLength());
        chain.setUpdated(OffsetDateTime.now());

        return repository.save(chain);
    }

    @Override
    public Optional<Chain> getOne(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Chain> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Chain> getActiveOne() {
        if (activeId.isPresent()) {
            return repository.findById(activeId.get());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void setActiveOne(String id) {
        getOne(id).orElseThrow(() -> new RuntimeException("shit"));
        activeId = Optional.of(id);
    }

    @Override
    public void removeActiveOne() {
        activeId = Optional.empty();
    }
}
