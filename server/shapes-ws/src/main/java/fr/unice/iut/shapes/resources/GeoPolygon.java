package fr.unice.iut.shapes.resources;

import fr.unice.iut.shapes.providers.PolygonCalculator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

public class GeoPolygon {

    // Les sommets ordonnes du polygone
    private List<GeoPoint> vertices;

    public GeoPolygon() {
        this.vertices = new ArrayList<GeoPoint>();
    }

    public List<GeoPoint> getVertices() {
        return vertices;
    }

    public void setVertices(List<GeoPoint> vertices) {
        this.vertices = vertices;
    }

    /**
     * Ajout d'un sommet au polygone
     *
     * @param point - le {@link GeoPoint a ajouter}
     */
    public void addVertex(GeoPoint point) {
        if (this.vertices == null) {
            this.vertices = new ArrayList<GeoPoint>();
        }
        this.vertices.add(point);
    }

    /**
     * Effectue la rotation du polygone par rapport au premier sommet defini.
     * Transformation dans un repere classique avant de reporter le resultat dans la projection de Mercator.
     *
     * @param angle - l'angle de rotation du polygone, en degres
     */
    public void rotate(Double angle) {

        //On récupère le point d'origine du polygon
        GeoPoint monGeoPointOrigin = new GeoPoint(this.getVertices().get(0).getLat(),this.getVertices().get(0).getLng());
        Point monPointOrigin = PolygonCalculator.fromLatLngToPoint(monGeoPointOrigin);

        //Boucle sur tous les points du poligon
        for (int i = 0; i < this.getVertices().size(); i++){

            GeoPoint monGeoPoint = new GeoPoint(this.getVertices().get(i).getLat(),this.getVertices().get(i).getLng());

            //On fait la rotation du point et on le retransforme en GeoPoint
            Point monPoint = PolygonCalculator.fromLatLngToPoint(monGeoPoint);
            monPoint.rotate(monPointOrigin, angle);
            monGeoPoint = PolygonCalculator.fromPointToLatLng(monPoint);

            //On met à jour les données du GeoPoint
            this.getVertices().get(i).setLat(monGeoPoint.getLat());
            this.getVertices().get(i).setLng(monGeoPoint.getLng());

        }

    }
}
