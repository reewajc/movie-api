package com.ram.app.repository;

import com.ram.app.model.Actor;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/actors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Actor Resource", description = "Operations related to actors")
public class ActorResource {

    @Inject
    EntityManager entityManager;

    @GET
    @Operation(summary = "Get all actors", description = "Returns a list of all actors in the database")
    @APIResponse(
            responseCode = "200",
            description = "List of actors",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Actor.class))
    )
    public List<Actor> getAllActors() {
        return entityManager.createQuery("SELECT a FROM Actor a", Actor.class).getResultList();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get actor by ID", description = "Returns an actor by their ID")
    @APIResponse(
            responseCode = "200",
            description = "The actor",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Actor.class))
    )
    @APIResponse(
            responseCode = "404",
            description = "Actor not found"
    )
    public Response getActorById(@PathParam("id") Short id) {
        Actor actor = entityManager.find(Actor.class, id);
        if (actor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(actor).build();
    }

    @GET
    @Path("/{id}/films")
    @Operation(summary = "Get films by actor ID", description = "Returns all films an actor has appeared in")
    @APIResponse(
            responseCode = "200",
            description = "List of films for the actor",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "Actor not found"
    )
    public Response getFilmsByActorId(@PathParam("id") Short id) {
        Actor actor = entityManager.find(Actor.class, id);
        if (actor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(actor.getFilms()).build();
    }
}