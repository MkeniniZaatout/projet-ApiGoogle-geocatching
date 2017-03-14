package fr.unice.iut.joueur.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;

/**
 * Interface pour un service web de gestion de zones.
 */
public interface JoueursService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllJoueurs();

    @GET
    @Path("/{joueurId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getJoueur(@PathParam("joueurId") String joueurId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response enoughPoint(@PathParam("joueurId") String joueurId, @QueryParam("NiveauZone") int levelZone);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response createJoueur(String joueurJson);

    @POST
    @Path("/{joueurId}")
    Response modifierPosition(@PathParam("joueurId") String joueurId, @QueryParam("lng") double lng, @QueryParam("lat") double lat);

}
