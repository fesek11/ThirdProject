package ua.fesenko.ThirdProject.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.fesenko.ThirdProject.dto.ListWrapper;
import ua.fesenko.ThirdProject.dto.MeasurementsDTO;
import ua.fesenko.ThirdProject.models.Measurements;
import ua.fesenko.ThirdProject.service.MeasurementsService;
import ua.fesenko.ThirdProject.util.MeasurementNotCreatedException;
import ua.fesenko.ThirdProject.util.SensorDuplicateException;
import ua.fesenko.ThirdProject.util.SensorErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;


    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
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
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    private MeasurementsDTO convertToMeasurementsDTO(Measurements measurements) {
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }

    @GetMapping
    public ListWrapper getAllMeasurements() {
        try {
            return new ListWrapper(measurementsService.getAllMeasurements().stream().map(this::convertToMeasurementsDTO).collect(Collectors.toList()));
        } catch (NullPointerException e) {
            throw new NullPointerException("There are no measurements");
        }
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        try {
            return measurementsService.getRainyDaysCountLong();
        } catch (NullPointerException e) {
            throw new NullPointerException("There are no rainy day measurements");
        }
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleSensorRegistrationNotExist(SensorDuplicateException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                "Sensor with this name not exist", System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
