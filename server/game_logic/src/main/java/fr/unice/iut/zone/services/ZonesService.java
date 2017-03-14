package fr.unice.iut.zone.services;

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

import java.util.Calendar;


/**
 * Interface pour un service web de gestion de zones.
 */
public interface ZonesService {

    @POST
    @Path("/{zoneId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response capturerZone(@PathParam("zoneId") String zoneId);

    @POST
    @Path("/{zoneId}")
    Response renforcerZone(@PathParam("zoneId") String zoneId, @QueryParam("niveauDifficulté") int niveauDifficulté);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllZones();

    @GET
    @Path("/{zoneId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getZone(@PathParam("zoneId") String zoneId, @QueryParam("lat") Double latitude,
                     @QueryParam("lng") Double longitude, @QueryParam("length") Double length,
                     @QueryParam("rot") Double rotation);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response createZone(String zoneJson);

    @POST
    @Path("/enhancement/{zoneId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response UpdateEnhancementTimer(@PathParam("zoneId") String  zoneId, @QueryParam("joueurId") String joueurId);

    @GET
    @Path("/enhancement/{zoneId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response CheckEnhancementTimer(@PathParam("zoneId") String  zoneId, @QueryParam("joueurId") String joueurId);


    @Consumes(MediaType.APPLICATION_JSON)
    Response setJoueurInZone(@QueryParam("zoneId") String zoneId, @QueryParam("nbJoueurs") int nbJoueurs);
}

