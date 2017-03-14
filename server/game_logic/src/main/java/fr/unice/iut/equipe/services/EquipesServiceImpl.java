package fr.unice.iut.equipe.services;

import fr.unice.iut.equipe.providers.EquipesProvider;
import fr.unice.iut.equipe.resources.Equipe;
import fr.unice.iut.joueur.providers.JoueursProvider;
import fr.unice.iut.joueur.resources.Joueur;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.ArrayList;

@Path("/equipes")
public class EquipesServiceImpl implements EquipesService {

    public Response getEquipe(String equipeId) {

        try {

            //On récupère l'equipe
            Equipe monEquipe = EquipesProvider.findEquipeById(equipeId);

            //On vérifie si elle existe
            if (monEquipe == null) {
                return Response.status(404).build();

            } else {
                return Response.ok(monEquipe).build();
            }

        } catch (NullPointerException e) {
            return Response.status(400).build();
        }
    }

    public Response getAllEquipes()
    {
        return Response.ok(EquipesProvider.getAllEquipes()).build();
    }

    public Response createEquipe(String equipeJson) {
        try {

            JSONObject maJsonEquipe = new JSONObject(equipeJson);
            JSONArray jsonArrayJoueurs = maJsonEquipe.getJSONArray("joueurs");
            List<Joueur> joueurs = new ArrayList<Joueur>();
            for(int i = 0; i < jsonArrayJoueurs.length(); i++){
                joueurs.add(JoueursProvider.findJoueurById(jsonArrayJoueurs.getJSONObject(i).getString("joueur")));
            }

            Equipe monEquipe = new Equipe(maJsonEquipe.getString("id"), joueurs, maJsonEquipe.getString("couleur"));

            //On crée la Shape
            EquipesProvider.createEquipe(monEquipe);

            return Response.status(201).entity(maJsonEquipe.getString("id")).build();

        } catch (JSONException e) {
            return Response.status(400).build();

        } catch (Exception e) {
            return Response.status(400).build();
        }
    }
}