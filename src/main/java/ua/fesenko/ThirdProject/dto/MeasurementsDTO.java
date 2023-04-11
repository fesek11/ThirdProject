package ua.fesenko.ThirdProject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ua.fesenko.ThirdProject.models.Sensor;

@Getter
@Setter
public class MeasurementsDTO {
    @Min(value = -100, message = "Input correct sensor`s value")
    @Max(value = 100, message = "Input correct sensor`s value")
    private float value;

    @NotNull(message = "Value should not be empty")
    private boolean raining;

    @NotNull(message = "Value should not be empty")
    private Sensor sensor;
}
