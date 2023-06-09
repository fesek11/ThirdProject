package ua.fesenko.ThirdProject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.fesenko.ThirdProject.repository.SensorRepository;

@SpringBootApplication
public class ThirdProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThirdProjectApplication.class, args);

    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}