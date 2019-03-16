package hu.unideb.inf.tesla.camera.webapp.web;

import hu.unideb.inf.tesla.camera.webapp.model.Chain;
import hu.unideb.inf.tesla.camera.webapp.service.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chain")
public class ChainControllerImpl implements ChainController {
    @Autowired
    private ChainService service;

    @Override
    @PutMapping
    public Chain createOne(
            @RequestBody ChainService.ChainRequest request
    ) {
        return service.createOne(request);
    }

    @Override
    @PostMapping
    public Chain updateOne(
            @RequestBody ChainService.ChainRequest request
    ) {
        return null;
    }

    @Override
    @GetMapping("/active")
    public Optional<Chain> getActiveOne() {
        return service.getActiveOne();
    }


    @Override
    @DeleteMapping("/active")
    public void removeActiveOne() {
        service.removeActiveOne();
    }

    @Override
    @PostMapping("/{id}/active")
    public void setActiveOne(
            @PathVariable String id
    ) {
        service.setActiveOne(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteOne(
            @PathVariable String id
    ) {
        service.deleteOne(id);
    }

    @Override
    @GetMapping("/{id}")
    public Optional<Chain> getOne(
            @PathVariable String id
    ) {
        return Optional.empty();
    }

    @Override
    @GetMapping
    public List<Chain> getAll() {
        return service.getAll();
    }
}
