package ua.fesenko.ThirdProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.fesenko.ThirdProject.repository.MeasurementsRepository;

@Service
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public String getAllMeasurements() {
    }

    public String getRainyDaysCount() {
    }
}
