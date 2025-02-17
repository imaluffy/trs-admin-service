package com.trs.trs_admin_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
public class TrainRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainRouteId;
    private String source;
    private String destination;
    private Time duration;
    private Integer distance;
    private Integer fare;
//    private Date arrivalTime;
//    private Date departureTime;
//    private String trainType;
}
