package ua.fesenko.ThirdProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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

    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Sensor sensor;
}