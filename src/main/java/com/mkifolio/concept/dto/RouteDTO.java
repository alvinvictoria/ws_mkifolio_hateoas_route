package com.mkifolio.concept.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Relation(value = "route", collectionRelation = "routes")
public class RouteDTO extends ResourceSupport implements Serializable {
    private Integer routeId;
    private String name;
    private String description;
    private List<RouteStopDTO> stops;

    public RouteDTO(){}
}
