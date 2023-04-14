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
import ua.fesenko.ThirdProject.util.MeasurementNotCreatedException;

import java.util.Collections;
import java.util.List;

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

    private List<MeasurementsDTO> convertToMeasurementsDTO(List<Measurements> measurements) {
        return Collections.singletonList(modelMapper.map(measurements, MeasurementsDTO.class));
    }

    @GetMapping
    public List<MeasurementsDTO> getAllMeasurements() {
        try {
            return convertToMeasurementsDTO(measurementsService.getAllMeasurements());
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

}
