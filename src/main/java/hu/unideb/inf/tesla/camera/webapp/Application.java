package hu.unideb.inf.tesla.camera.webapp;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.web.WebApplicationInitializer;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class Application
        extends SpringBootServletInitializer
        implements WebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    @PropertySource("classpath:application.properties")
    public class MongoConfiguration extends AbstractMongoConfiguration {
        @Value("${spring.data.mongodb.host}")
        private String client;

        @Value("${spring.data.mongodb.database}")
        private String database;

        @Override
        public MongoClient mongoClient() {
            return new MongoClient(this.client);
        }

        @Override
        protected String getDatabaseName() {
            return this.database;
        }

        @Override
        public CustomConversions customConversions() {
            return new MongoCustomConversions(Arrays.asList(new DateToOffsetDateTimeConverter(), new OffsetDateTimeToDateConverter()));
        }

        class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {

            @Override
            public OffsetDateTime convert(Date source) {
                return source == null ? null : OffsetDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
            }

        }

        class OffsetDateTimeToDateConverter implements Converter<OffsetDateTime, Date> {

            @Override
            public Date convert(OffsetDateTime source) {
                return source == null ? null : Date.from(source.toInstant());
            }

        }
    }

}
