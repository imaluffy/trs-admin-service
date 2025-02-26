package com.trs.trs_admin_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "trains")
public class Train {
    @Id
    private Integer trainNumber;
    private String trainName;
    private String trainSource;
    private String trainDestination;
    private Integer trainBaseFare;
    private Integer slCoachesNum;
    private Integer acCoachesNum;
    private Status trainStatus;

    @ElementCollection
    private List<TrainStop> trainStops;

    public enum Status{
        RUNNING, DELAYED, CANCELLED
    }

}
