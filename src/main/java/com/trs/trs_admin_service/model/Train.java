package com.trs.trs_admin_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "trains")
public class Train {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long trainId;
    private String trainName;
    @Column(unique = true)
    private Integer trainNumber;
    private String trainSource;
    private String trainDestination;
    private Integer trainBaseFare;
    private Integer trainCapacity;
    private Integer trainBookedSeats;
    private String trainStatus;

    @ElementCollection
    private List<TrainStop> trainStops;

}
