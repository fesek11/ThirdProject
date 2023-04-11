package ua.fesenko.ThirdProject.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MeasurementErrorResponse {
    private String message;
    private long timestamp;

}
