package hu.unideb.inf.tesla.camera.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Builder
@Data
@Document
@NoArgsConstructor
@JsonIgnoreProperties({"content", "contentType"})
public class Image {
    private String id;
    private String content;
    private String name;
    private String contentType;
    private double longitude;
    private double latitude;
    private String mac;
    private OffsetDateTime created;
}
