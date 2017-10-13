package com.mkifolio.concept.manager;

import com.mkifolio.concept.dto.RouteDTO;
import com.mkifolio.concept.entity.Route;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRouteManager {
    List<RouteDTO> getRoutes();

    RouteDTO getRouteById(Integer id);

    RouteDTO saveRoute(RouteDTO routeDTO);
}
