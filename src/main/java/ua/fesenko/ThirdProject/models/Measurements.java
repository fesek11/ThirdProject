package ua.fesenko.ThirdProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "Measurements")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Measurements {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Value of temperature should not be empty")
    @Min(value = -100, message = "Input correct sensor`s value")
    @Max(value = 100, message = "Input correct sensor`s value")
    @Column(name = "value")
    private float value;

    @NotNull(message = "Value is raining should not be empty")
    @Column(name = "raining")
    private boolean raining;

    @NotNull(message = "Value of creating should not be empty")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull(message = "Value of updating should not be empty")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @JoinColumn(name = "sensors_name", referencedColumnName = "name")
    @ManyToOne
    private Sensor sensor;

    public Measurements(float value, boolean raining, LocalDateTime createdAt, LocalDateTime updatedAt, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sensor = sensor;
    }
}