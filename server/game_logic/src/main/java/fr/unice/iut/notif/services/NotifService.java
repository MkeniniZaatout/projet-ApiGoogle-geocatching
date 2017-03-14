package fr.unice.iut.notif.services;

import fr.unice.iut.zone.resources.Zone;
/**
 * Created by Ismael on 09/12/2016.
 */
import fr.unice.iut.zone.resources.Zone;
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



public interface NotifService {
    @GET   // this method process GET request from client
    @Produces(MediaType.APPLICATION_JSON)
    Response getMsgNotif();

    @GET
    @Path("/infoWindow")
    @Produces(MediaType.APPLICATION_JSON)
    Response getInfoWindow();

    @GET
    @Path("/{shapeId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response createZones(@PathParam("shapeId") String shapeId);

}
