package ua.fesenko.ThirdProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "MeasurementsController")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Measurements {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Value should not be empty")
    @Min(value = -100, message = "Input correct sensor`s value")
    @Max(value = 100, message = "Input correct sensor`s value")
    @Column(name = "value")
    private float value;

    @NotEmpty(message = "Value should not be empty")
    @Column(name = "raining")
    private boolean raining;

    @NotEmpty(message = "Value should not be empty")
    @Column(name = "created_at")
    private Date createdAt;

    @JoinColumn(name = "sensor_id", referencedColumnName = "sensor_name")
    @ManyToOne
    @NotEmpty(message = "Value should not be empty")
    private Sensor sensor;


}