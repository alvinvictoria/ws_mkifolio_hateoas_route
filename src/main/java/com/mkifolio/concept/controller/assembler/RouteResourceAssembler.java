package com.mkifolio.concept.controller.assembler;

import com.mkifolio.concept.controller.RouteController;
import com.mkifolio.concept.dto.RouteDTO;
import com.mkifolio.concept.entity.Route;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RouteResourceAssembler extends ResourceAssemblerSupport<RouteDTO,Resource> {

    public RouteResourceAssembler(){
        super(RouteDTO.class, Resource.class);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends RouteDTO> routes) {
        List<Resource> resources = new ArrayList<>();

        for(RouteDTO routeDTO: routes){
            resources.add(new Resource<RouteDTO>(routeDTO, linkTo(methodOn(RouteController.class).getRoutes()).slash(routeDTO.getId()).withSelfRel()));
        }
        return super.toResources(routes);
    }

    @Override
    public Resource toResource(RouteDTO routeDTO) {
        return new Resource<RouteDTO>(routeDTO, linkTo(RouteController.class).slash(routeDTO.getId()).withSelfRel());
    }
}
