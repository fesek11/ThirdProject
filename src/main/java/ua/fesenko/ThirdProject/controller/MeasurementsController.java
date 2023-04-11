package ua.fesenko.ThirdProject.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.fesenko.ThirdProject.dto.MeasurementsDTO;
import ua.fesenko.ThirdProject.models.Measurements;
import ua.fesenko.ThirdProject.service.MeasurementsService;
import ua.fesenko.ThirdProject.service.SensorService;
import ua.fesenko.ThirdProject.util.MeasurementNotCreatedException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;


    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, SensorService sensorService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementsDTO measurementsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();

            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError f : fieldErrorList) {
                builder.append(f.getField()).append(" - ").append(f.getDefaultMessage()).append(";");
            }
            throw new MeasurementNotCreatedException(builder.toString());
        }

        Measurements measurement = convertToMeasurements(measurementsDTO);
        try {
            measurementsService.addMeasurements(measurement);
        } catch (Exception e) {
            throw new MeasurementNotCreatedException(e.toString());
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurements convertToMeasurements(MeasurementsDTO measurementsDTO) {
        Measurements measurements = new Measurements();


        measurements.setValue(measurementsDTO.getValue());
        measurements.setRaining(measurementsDTO.isRaining());
        measurements.setSensor(measurementsDTO.getSensor());
        measurements.setCreatedAt(LocalDateTime.now());
        measurements.setUpdatedAt(LocalDateTime.now());

        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    @GetMapping
    public List<Measurements> getAllMeasurements() {
        return measurementsService.getAllMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public List<Measurements> getRainyDaysCount() {
        return measurementsService.getRainyDaysCount();
    }

}
