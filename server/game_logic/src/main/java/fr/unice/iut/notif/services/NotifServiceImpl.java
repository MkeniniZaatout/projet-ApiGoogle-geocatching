package fr.unice.iut.notif.services;

import fr.unice.iut.notif.providers.NotifProvider;
import fr.unice.iut.zone.persistance.ZonePersi;
import fr.unice.iut.zone.resources.Zone;
import fr.unice.iut.notif.resources.Notif;
import javax.ws.rs.core.Response;
import java.io.Console;
import javax.ws.rs.Path;
import java.util.ArrayList;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Ismael on 09/12/2016.
 */

@Path("notif")
public class NotifServiceImpl implements NotifService{


    @Override
    public Response getMsgNotif() {
        System.out.println("message de notification");
        return Response.ok(NotifProvider.getMsgNotif()).build();
    }

    @Override
    public Response getInfoWindow() {
        System.out.println("infoWindow");
        return Response.ok(NotifProvider.getInfoWindow()).build();
    }

    @Override
    	public Response createZones(String shapeId) {

    		ArrayList<String> infoZones = new ArrayList<String>();
    		// array de string du genre : rectangle?lat=43.61678&lng=7.07177&length=0.000155&rot=97
        // le Contenue est le même que dans la table areas(zone) de la Bd
        infoZones.add(shapeId+"?lat=43.70614939132079&lng=7.2642105817794&length=0.5&rot=50");

    		JSONArray JsonArrayFormes = new JSONArray();
    		String reponse = "";
    		String charset = "UTF-8";
    		for (String zone:infoZones) {
    		try {
        		String url = "http://localhost:8080/shapes-ws/rest/shapes/" + zone;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    }
                    in.close();

            JSONObject maForm = new JSONObject(response.toString());
            JsonArrayFormes.put(maForm.getJSONArray("vertices"));

         }
    			catch(Exception e){}

    		}
    		return Response.ok(JsonArrayFormes).build();

    	}
}
