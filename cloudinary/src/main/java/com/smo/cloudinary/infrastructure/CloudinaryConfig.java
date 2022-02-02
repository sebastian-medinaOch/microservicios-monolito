package com.smo.cloudinary.infrastructure;


import com.smo.cloudinary.domain.CloudinaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("bootstrap.yml")
public class CloudinaryConfig {

    @Autowired
    Environment env;

    @Bean
    public CloudinaryModel cloudinaryModel() {
        return new CloudinaryModel(env.getProperty("cloud_name"), env.getProperty("api_key"), env.getProperty("api_secret"));
    }
}
