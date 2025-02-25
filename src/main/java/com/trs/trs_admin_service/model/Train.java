package com.trs.trs_admin_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.Map;

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
    private int trainBaseFare;
    private int trainCapacity;
    private int trainBookedSeats;
    private String trainStatus;

    @ElementCollection
    private Map<Date,TrainStop> trainStops;
//    private List<Pair<String, Time>> trainStops;
//    private Time trainDepartureTime;
//    private Time trainArrivalTime;
//    private String trainType;
}
