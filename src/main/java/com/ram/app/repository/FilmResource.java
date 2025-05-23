package com.ram.app.repository;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class FilmResource {

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello World!";
    }
}
