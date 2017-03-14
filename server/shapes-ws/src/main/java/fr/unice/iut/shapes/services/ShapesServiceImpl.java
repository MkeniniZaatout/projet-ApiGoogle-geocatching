package fr.unice.iut.shapes.services;

import fr.unice.iut.shapes.exception.ShapeAlreadyExistException;
import fr.unice.iut.shapes.providers.PolygonCalculator;
import fr.unice.iut.shapes.providers.ShapesProvider;
import fr.unice.iut.shapes.resources.GeoPoint;
import fr.unice.iut.shapes.resources.GeoPolygon;
import fr.unice.iut.shapes.resources.Point;
import fr.unice.iut.shapes.resources.Shape;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.Console;

import static fr.unice.iut.shapes.providers.PolygonCalculator.fromLatLngToPoint;

@Path("/shapes")
public class ShapesServiceImpl implements ShapesService {

    @Override
    public Response getAllShapes() {
        System.out.println("AllShapes");
        return Response.ok(ShapesProvider.getAllShapes()).build();
    }

    @Override
    public Response getShape(String shapeId, Double latitude, Double longitude, Double length, Double rotation) {

        try{

            //On récupère la Shape
            Shape maShape = ShapesProvider.findShapeById(shapeId);

            //On vérifie si elle existe
            if(maShape == null){
                return Response.status(404).build();

            } else {

                //On crée un GeoPolygon
                GeoPolygon monGeoPolygon = PolygonCalculator.transformShape(maShape.getPoints(), new GeoPoint(latitude, longitude), length, rotation);

                return Response.ok(monGeoPolygon).build();
            }

        }catch(NullPointerException e) {
            return Response.status(400).build();
        }

    }

    @Override
    public Response registerShape(String shapeJson) {

        try{

            JSONObject maJsonShape = new JSONObject(shapeJson);
            Shape maShape = new Shape(maJsonShape.getString("id"));

            JSONArray mesPoints = maJsonShape.getJSONArray("points");

            //Boucle sur tous les points de la Shape
            for (int i = 0; i < mesPoints.length(); i++){
                JSONObject monPoint = mesPoints.getJSONObject(i);

                //On ajoute le point dans la Shape
                maShape.addPoint(new Point(monPoint.getDouble("x"), monPoint.getDouble("y")));
            }

            //On crée la Shape
            ShapesProvider.createShape(maShape);

            return Response.status(201).entity(maJsonShape.getJSONArray("points")).build();

        } catch (JSONException e){
            return Response.status(400).build();

        } catch (ShapeAlreadyExistException e){
            return  Response.status(400).build();
        }
    }

    public Response deleteShape(String shapeId) {

        //On récupère la Shape
        Shape maShape = ShapesProvider.findShapeById(shapeId);

        //On vérifie si elle existe
        if(maShape == null){
            return Response.status(404).build();
        }

        Boolean OK = ShapesProvider.deleteShape(maShape);

        if(OK){
            return Response.status(204).build();
        } else {
            return Response.status(500).build();
        }

    }
}
