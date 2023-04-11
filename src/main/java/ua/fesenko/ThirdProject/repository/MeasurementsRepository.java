package ua.fesenko.ThirdProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.fesenko.ThirdProject.models.Measurements;

import java.util.List;

public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
    List<Measurements> findByRaining(boolean raining);

}
