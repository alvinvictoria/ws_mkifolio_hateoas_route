package com.mkifolio.concept.controller;

import com.mkifolio.concept.controller.assembler.RouteResourceAssembler;
import com.mkifolio.concept.dto.RouteDTO;
import com.mkifolio.concept.entity.Route;
import com.mkifolio.concept.manager.IRouteManager;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(path = "/routes")
public class RouteController {
    private IRouteManager routeManager;
    private RouteResourceAssembler assembler;

    public RouteController(IRouteManager routeManager, RouteResourceAssembler assembler) {
        this.routeManager = routeManager;
        this.assembler = assembler;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity getRoutes() {
        List<RouteDTO> listings = routeManager.getRoutes();

        return CollectionUtils.isEmpty(listings) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(assembler.toResources(listings));

    }

    @GetMapping(path = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity getRouteById(@PathVariable(name = "id") Integer id) {
        RouteDTO routeDTO = routeManager.getRouteById(id);

        return routeDTO != null ?
                ResponseEntity.ok(assembler.toResource(routeDTO)) :
                ResponseEntity.notFound().build();

    }

    @PostMapping()
    public ResponseEntity createRoute(@RequestBody RouteDTO routeDTO) {
        RouteDTO newRoute = routeManager.saveRoute(routeDTO);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateRoute(@PathVariable(name = "id") String id, @RequestBody Route route) {
        return null;
    }
}
