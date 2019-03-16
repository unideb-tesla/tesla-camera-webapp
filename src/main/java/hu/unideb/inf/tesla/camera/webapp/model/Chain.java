package hu.unideb.inf.tesla.camera.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Builder
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"created", "updated"})
public class Chain {
    @Id
    private String id;
    private String name;
    private int length;
    private int intervalDuration;
    private int delay;
    private OffsetDateTime created;
    private OffsetDateTime updated;
}
