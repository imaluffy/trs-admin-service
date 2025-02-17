package com.trs.trs_admin_service.model.dto;

import lombok.Data;

import java.sql.Time;

@Data
public class TrainRouteDTO {
    private String source;
    private String destination;
    private Time duration;
    private Integer distance;
    private Integer fare;
}
