package ua.fesenko.ThirdProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.fesenko.ThirdProject.models.Measurements;

public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

}
