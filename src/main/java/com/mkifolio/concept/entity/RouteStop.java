package com.mkifolio.concept.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ROUTESTOP")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RouteStop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROUTESTOPID")
    private Integer stopId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTEID", nullable = false)
    @NotNull(message = "mandatory field")
    private Route route;

    private Integer sequence;
    private String origin;
    private String destination;
    private Double distance;
    private String unitOfMeasurement;

    public RouteStop(){}
}
