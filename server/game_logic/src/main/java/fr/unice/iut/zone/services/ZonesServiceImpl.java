package fr.unice.iut.zone.services;

import fr.unice.iut.joueur.providers.JoueursProvider;
import fr.unice.iut.joueur.resources.Joueur;
import fr.unice.iut.zone.providers.ZonesProvider;
import fr.unice.iut.zone.resources.Zone;
import fr.unice.iut.equipe.resources.Equipe;
import fr.unice.iut.equipe.providers.EquipesProvider;
import fr.unice.iut.zone.providers.ZoneCalculator;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/zone")
public class ZonesServiceImpl implements ZonesService {

    public Response getZone(String zoneId, Double latitude, Double longitude, Double length, Double rotation) {
        JSONObject jsonZone = new JSONObject();
        try {

            //On récupère la zone
            Zone maZone = ZonesProvider.findZoneById(zoneId);

            //On vérifie si elle existe
            if (maZone == null) {
                return Response.status(404).build();

            } else {
                try {
                    String url = "http://localhost:8080/shapes-ws/rest/shapes/" + maZone.getShape()+"?lat="+ latitude +"&lng=" + longitude + "&length=" + length;
                    if (rotation == 0) {
                        url += "&rot=" + rotation;
                    }

                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    // optional default is GET
                    con.setRequestMethod("GET");

                    //add request header
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject maForm = new JSONObject(response.toString());
                    jsonZone.put("id",maZone.getNom());
                    jsonZone.put("vertices",maForm.getJSONArray("vertices"));
                    jsonZone.put("color",maZone.getEquipe().getCouleur());
                    jsonZone.put("equipe",maZone.getEquipe());
                    jsonZone.put("niveau",maZone.getNiveau());

                    return Response.ok(jsonZone).build();
                } catch (Exception e) {
                    return Response.status(400).build();
                }
            }
        } catch (NullPointerException e) {
            return Response.status(400).build();
        }
    }

    public Response getAllZones()
    {
        return Response.ok(ZonesProvider.getAllZones()).build();
    }

    public Response renforcerZone(String zoneId, int niveauDifficulté) {
        Zone maZone = ZonesProvider.findZoneById(zoneId);
        if (maZone == null) {
            return Response.status(404).build();
        } else{
            ZoneCalculator.renforcer(maZone,niveauDifficulté);
            return Response.status(200).build();
        }
    }

    public Response capturerZone(String zoneId) {
        Zone maZone = ZonesProvider.findZoneById(zoneId);
        if (maZone == null) {
            return Response.status(404).build();
        } else {
            ZoneCalculator.capturer(maZone);
            return Response.status(200).build();
        }
    }

    public Response setJoueurInZone(String zoneId, int nbJoueurs)
    {
        try{
            Zone maZone = ZonesProvider.findZoneById(zoneId);
            maZone.setNbJoueurInZone(nbJoueurs);
            return Response.status(200).build();
        } catch (Exception e){
            return Response.status(404).build();
        }
    }

    public Response createZone(String zoneJson) {
        try {
            Zone maZone;
            JSONObject maJsonZone = new JSONObject(zoneJson);
            Equipe equipe = EquipesProvider.findEquipeById(maJsonZone.getString("equipe"));

            if(maJsonZone.getString("equipe") == "") {
                maZone = new Zone(maJsonZone.getString("id"), maJsonZone.getString("shape"));
            }
            else {
                maZone = new Zone(maJsonZone.getString("id"), maJsonZone.getString("shape"), equipe);
            }

            //On crée la Zone
            ZonesProvider.createZone(maZone);

            return Response.status(201).entity(maJsonZone.getString("shape")).build();

        } catch (JSONException e) {
            return Response.status(400).build();

        } catch (Exception e) {
            return Response.status(400).build();
        }
    }
	
	/*
	@Override
	public Response createZones(String zoneId) {
	
		ArrayList<String> infoZones = GameProvider.getGame();
			//array de string du genre : rectangle?lat=43.61678&lng=7.07177&length=0.000155&rot=97
 
 
		JSONArray JsonArrayFormes = new JSONArray();
		String reponse = "";
		String charset = "UTF-8";
	
		for (String zone:infoZones) {
		try {
		
		
			}
			catch(Exception e){}
	
		}
		return Response.ok(jsonShapes).build();
	
	}
	
	*/

    public Response UpdateEnhancementTimer(String zoneJson, String idJoueur)
    {
        try{

            JSONObject maJsonZone = new JSONObject(zoneJson);
            Zone maZone = ZonesProvider.findZoneById(maJsonZone.getString("id"));

            maZone.setEnhancementTimers(JoueursProvider.findJoueurById(idJoueur), Calendar.getInstance());

            return Response.ok().build();
        }catch (Exception e){
            return Response.status(400).build();
        }
    }

    public Response CheckEnhancementTimer(String zoneJson, String idJoueur) {
        try {

            JSONObject maJsonZone = new JSONObject(zoneJson);
            Zone maZone = ZonesProvider.findZoneById(maJsonZone.getString("id"));

            Calendar monInstant = Calendar.getInstance();
            monInstant.add(Calendar.MINUTE, -1);

            if (maZone.getEnhancementTimer(JoueursProvider.findJoueurById(idJoueur)).after(monInstant)) {
                return Response.ok(true).build();
            } else {
                return Response.ok(false).build();
            }

        } catch (Exception e) {
            return Response.status(400).build();
        }

    }

}
