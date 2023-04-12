package ua.fesenko.ThirdProject.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.fesenko.ThirdProject.dto.SensorDTO;
import ua.fesenko.ThirdProject.models.Sensor;
import ua.fesenko.ThirdProject.service.SensorService;
import ua.fesenko.ThirdProject.util.SensorDuplicateException;
import ua.fesenko.ThirdProject.util.SensorErrorResponse;
import ua.fesenko.ThirdProject.util.SensorNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> sensorRegistration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();

            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError f : fieldErrorList
            ) {
                builder.append(f.getField()).append(" - ").append(f.getDefaultMessage()).append(";");
            }
            throw new SensorNotCreatedException(builder.toString());
        }
        Sensor sensor = convertToSensor(sensorDTO);

        try {
            sensorService.doRegister(sensor);
        } catch (Exception e) {
            throw new SensorDuplicateException(e.toString());
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleSensorRegistrationIncorrectInput(SensorNotCreatedException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                "Sensor was not created", System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleSensorRegistrationAlreadyExist(SensorDuplicateException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                "Sensor with this name already exist", System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
