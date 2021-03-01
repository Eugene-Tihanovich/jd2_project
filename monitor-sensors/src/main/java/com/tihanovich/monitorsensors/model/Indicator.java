package com.tihanovich.monitorsensors.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Indicator {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer indicatorId;

    private String indicatorType;

    private String indicatorUnit;

}