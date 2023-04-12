package ua.fesenko.ThirdProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.fesenko.ThirdProject.models.Measurements;
import ua.fesenko.ThirdProject.models.Sensor;
import ua.fesenko.ThirdProject.repository.MeasurementsRepository;
import ua.fesenko.ThirdProject.repository.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorRepository sensorRepository) {
        this.measurementsRepository = measurementsRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurements> getAllMeasurements() {
        return measurementsRepository.findAll();
    }

    public List<Measurements> getRainyDaysCount() {
        return measurementsRepository.findByRaining(true);
    }

    @Transactional
    public void addMeasurements(Measurements measurement) {

        fullTheMeasurement(measurement);
        measurementsRepository.save(measurement);

//        if (exists.isPresent()) {
////             Hibernate.initialize(exists.get());
//            measurementsRepository.saveAndFlush(measurement);
////            exists.get().setMeasurements(Collections.singletonList(measurement));
//        } else {
//            throw new RuntimeException("The sensor is not exist!");
//        }
    }
    private void fullTheMeasurement(Measurements measurement) {
     measurement.setSensor(sensorRepository.findByName(measurement.getSensor().getName()).get());
     measurement.setCreatedAt(LocalDateTime.now());
     measurement.setUpdatedAt(LocalDateTime.now());
    }
}
