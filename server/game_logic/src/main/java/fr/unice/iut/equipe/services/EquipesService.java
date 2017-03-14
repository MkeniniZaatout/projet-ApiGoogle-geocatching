package fr.unice.iut.equipe.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Interface pour un service web de gestion de zones.
 */
public interface EquipesService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllEquipes();

    @GET
    @Path("/{equipeId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getEquipe(@PathParam("equipeId") String equipeId);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response createEquipe(String equipeJson);
}
