package ua.fesenko.ThirdProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.fesenko.ThirdProject.models.Sensor;
import ua.fesenko.ThirdProject.repository.SensorRepository;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void doRegister(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
