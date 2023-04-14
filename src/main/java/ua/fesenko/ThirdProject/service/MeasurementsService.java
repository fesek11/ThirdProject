package ua.fesenko.ThirdProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.fesenko.ThirdProject.models.Measurements;
import ua.fesenko.ThirdProject.repository.MeasurementsRepository;
import ua.fesenko.ThirdProject.repository.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;

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

    public Long getRainyDaysCountLong() {
        return measurementsRepository.countByRainingIsTrue();
    }

    @Transactional
    public void addMeasurements(Measurements measurement) {
        fillTheMeasurement(measurement);
        measurementsRepository.save(measurement);
    }
    private void fillTheMeasurement(Measurements measurement) {
     measurement.setSensor(sensorRepository.findByName(measurement.getSensor().getName()).get());
     measurement.setCreatedAt(LocalDateTime.now());
     measurement.setUpdatedAt(LocalDateTime.now());
    }
}
