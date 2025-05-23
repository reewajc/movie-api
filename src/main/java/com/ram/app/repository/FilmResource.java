package com.ram.app.repository;

import com.ram.app.model.Film;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Film Resource", description = "Operations related to films")
public class FilmResource {

    @Inject
    EntityManager entityManager;

    @GET
    @Operation(summary = "Get all films", description = "Returns a list of all films in the database")
    @APIResponse(
            responseCode = "200",
            description = "List of films",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Film.class))
    )
    public List<Film> getAllFilms() {
        return entityManager.createQuery("SELECT f FROM Film f", Film.class).getResultList();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get film by ID", description = "Returns a film by its ID")
    @APIResponse(
            responseCode = "200",
            description = "The film",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Film.class))
    )
    @APIResponse(
            responseCode = "404",
            description = "Film not found"
    )
    public Response getFilmById(@PathParam("id") Short id) {
        Film film = entityManager.find(Film.class, id);
        if (film == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(film).build();
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Hello endpoint", description = "Returns a hello message")
    @APIResponse(
            responseCode = "200",
            description = "Hello message",
            content = @Content(mediaType = MediaType.TEXT_PLAIN)
    )
    public String hello() {
        return "Hello World!";
    }
}
