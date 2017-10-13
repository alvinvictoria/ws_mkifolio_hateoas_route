package com.mkifolio.concept.manager.impl;

import com.mkifolio.concept.dto.RouteDTO;
import com.mkifolio.concept.entity.Route;
import com.mkifolio.concept.manager.IRouteManager;
import com.mkifolio.concept.repository.RouteRepository;
import com.mkifolio.concept.repository.RouteStopRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RouteManagerImpl implements IRouteManager {
    private RouteRepository routeRepository;
    private RouteStopRepository routeStopRepository;
    private ModelMapper modelMapper;

    public RouteManagerImpl(RouteRepository routeRepository,
                            RouteStopRepository routeStopRepository,
                            ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.routeStopRepository = routeStopRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteDTO> getRoutes() {
        List<Route> entities = routeRepository.findAll();
        return entities.stream().map(entity -> convertToDTO(entity)).collect(Collectors.toList());
    }

    @Override
    public RouteDTO getRouteById(Integer id) {
        return convertToDTO(routeRepository.findOne(id));
    }

    @Override
    @Transactional
    public RouteDTO saveRoute(RouteDTO routeDTO) {
        Route route = convertToEntity(routeDTO);

        return convertToDTO(routeRepository.save(route));
    }

    private RouteDTO convertToDTO(Route route) {
        return modelMapper.map(route, RouteDTO.class);
    }

    private Route convertToEntity(RouteDTO routeDTO) {
        return modelMapper.map(routeDTO, Route.class);
    }
}
