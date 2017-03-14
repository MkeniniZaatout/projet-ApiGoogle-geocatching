package fr.unice.iut.joueur.services;

import fr.unice.iut.joueur.providers.JoueursProvider;
import fr.unice.iut.joueur.resources.Joueur;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Calendar;

@Path("/joueurs")
public class JoueursServiceImpl implements JoueursService {

    public Response getJoueur(String joueurId) {

        try {

            //On récupère la Joueur
            Joueur monJoueur = JoueursProvider.findJoueurById(joueurId);

            //On vérifie si elle existe
            if (monJoueur == null) {
                return Response.status(404).build();

            } else {
                return Response.ok(monJoueur).build();
            }

        } catch (NullPointerException e) {
            return Response.status(400).build();
        }
    }

    public Response enoughPoint(String joueurId, int levelZone){
            Joueur monJoueur = JoueursProvider.findJoueurById(joueurId);
            if((levelZone*10) > monJoueur.getPoint())
            {
                return Response.ok(false).build();
            }
            else
            {
                return Response.ok(true).build();
            }
    }

    public Response getAllJoueurs()
    {
        return Response.ok(JoueursProvider.getAllJoueurs()).build();
    }

    public Response createJoueur(String joueurJson) {
        try {

            JSONObject maJsonJoueur = new JSONObject(joueurJson);
            Joueur monJoueur = new Joueur(maJsonJoueur.getString("id"), maJsonJoueur.getDouble("lat"), maJsonJoueur.getDouble("lng"));

            //On crée le Joueur
            JoueursProvider.createJoueur(monJoueur);
            return Response.status(201).entity(maJsonJoueur.getString("id")).build();

        } catch (JSONException e) {
            return Response.status(400).build();

        } catch (Exception e) {
            return Response.status(400).build();
        }
    }

    public Response modifierPosition(String idJoueur, double lng, double lat)
    {
        try{
            Joueur monJoueur = JoueursProvider.findJoueurById(idJoueur);
            monJoueur.setLat(lat);
            monJoueur.setLng(lng);
            return Response.ok().build();
        }catch(NullPointerException e) {
            return Response.status(400).build();
        }
    }

}
