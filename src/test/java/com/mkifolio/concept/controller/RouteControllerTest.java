package com.mkifolio.concept.controller;

import com.mkifolio.concept.controller.assembler.RouteResourceAssembler;
import com.mkifolio.concept.dto.RouteDTO;
import com.mkifolio.concept.manager.IRouteManager;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class RouteControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IRouteManager routeManager;

    @Mock
    private RouteResourceAssembler assembler;

    @InjectMocks
    private RouteController routeController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(routeController).addFilters(new CorsFilter()).build();
    }

    @Test
    public void testGetRoutesWhenSuccess() throws Exception{
        List<RouteDTO> listing = new ArrayList<RouteDTO>();
        listing.add(RouteDTO.builder().routeId(1).name("test").description("test success").build());

        Mockito.when(routeManager.getRoutes()).thenReturn(listing);

        mockMvc.perform(get("/routes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"));
    }

    @Test
    public void testGetRoutesWhenNoResource() throws Exception{
        Mockito.when(routeManager.getRoutes()).thenReturn(new ArrayList<RouteDTO>());

        mockMvc.perform(get("/routes"))
                .andExpect(status().isNotFound());

    }

}
