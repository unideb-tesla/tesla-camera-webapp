package hu.unideb.inf.tesla.camera.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Builder
@Data
@Document
@NoArgsConstructor
@JsonIgnoreProperties({"created", "updated"})
public class Device {
    @Id
    private String mac;
    private String brand;
    private String device;
    private String model;
    private String sdk;
    private OffsetDateTime created;
    private OffsetDateTime updated;
    private int frequency;
    private String name;
}
