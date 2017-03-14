package fr.unice.iut.shapes.services;

import fr.unice.iut.shapes.resources.Shape;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Interface pour un service web de gestion de formes.
 */
public interface ShapesService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllShapes();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response registerShape(String shapeJson);

    @GET
    @Path("/{shapeId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getShape(@PathParam("shapeId") String shapeId, @QueryParam("lat") Double latitude,
                      @QueryParam("lng") Double longitude, @QueryParam("length") Double length,
                      @QueryParam("rot") Double rotation);

    @DELETE
    @Path("/{shapeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response deleteShape(@PathParam("shapeId") String shapeId);

}
