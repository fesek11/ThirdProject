package ua.fesenko.ThirdProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.fesenko.ThirdProject.models.Measurements;
import ua.fesenko.ThirdProject.service.MeasurementsService;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @PostMapping("/add")
    public String addMeasurements() {

    }

    @GetMapping
    public String getAllMeasurements() {
       return measurementsService.getAllMeasurements();
    }

    @PostMapping("/rainyDaysCount")
    public String getRainyDaysCount() {
        return measurementsService.getRainyDчaysCount();
    }

}
