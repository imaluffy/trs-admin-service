package com.trs.trs_admin_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "base_data")
public class TrainStopBase {
    @Id
    private Integer trainNumber;
    private String trainName;
    @ElementCollection
    List<TrainStop> trainStops;
}
