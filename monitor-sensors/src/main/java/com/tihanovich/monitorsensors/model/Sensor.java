package com.tihanovich.monitorsensors.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sensorId;

    @NotBlank
    @Size(max = 30)
    @Column(length = 30)
    private String sensorName;

    @NotBlank
    @Size(max = 15)
    @Column(length = 15)
    private String sensorModel;

    @Column
    private Integer sensorRangeFrom;

    @Column
    private Integer sensorRangeTo;

    @ManyToOne
    @JoinColumn(name = "idFromIndicator")
    private Indicator idFromIndicator;

    @Size(max = 40)
    @Column(length = 40)
    private String sensorLocation;

    @Size(max = 200)
    @Column(length = 200)
    private String sensorDescription;
}