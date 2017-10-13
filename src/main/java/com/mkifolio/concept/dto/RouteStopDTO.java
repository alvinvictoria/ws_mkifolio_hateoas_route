package com.mkifolio.concept.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@Relation(value = "stop", collectionRelation = "stops")
public class RouteStopDTO extends ResourceSupport implements Serializable {
    private Integer stopId;
    private Integer sequence;
    private String origin;
    private String destination;
    private Double distance;
    private String unitOfMeasurement;

    public RouteStopDTO(){}
}
