package com.trs.trs_admin_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.sql.Time;
import java.util.List;

@Entity
@Data
@Table(name = "trains")
public class Train {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long trainId;
    private String trainName;
    private Integer trainNumber;
    private String trainSource;
    private String trainDestination;
    private int trainBaseFare;
    private int trainCapacity;
    private int trainBookedSeats;
    private String trainStatus;

    @ElementCollection
    private List<TrainStop> trainStops;
//    private List<Pair<String, Time>> trainStops;
//    private Time trainDepartureTime;
//    private Time trainArrivalTime;
//    private String trainType;
}
