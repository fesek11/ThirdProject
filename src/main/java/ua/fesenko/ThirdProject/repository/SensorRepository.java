package ua.fesenko.ThirdProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.fesenko.ThirdProject.models.Sensor;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Optional<Sensor> findById(Integer id);
    Optional<Sensor> findByName(String name);

}
