package fr.unice.iut.shapes.providers;

import fr.unice.iut.shapes.resources.GeoPoint;
import fr.unice.iut.shapes.resources.Point;
import fr.unice.iut.shapes.resources.GeoPolygon;

import java.util.List;

import static java.lang.Math.pow;

/**
 * Classe utilitaire pour effectuer des calculs sur les Points et GeoPoints.
 */
public class PolygonCalculator {

    private PolygonCalculator() {
    }

    /**
     * Transforme une forme definie en une forme dans un repere geographique latitude-longitude.
     *
     * @param shape - La liste de points constituant le forme de base.
     * @param origin - Le {@link GeoPoint} du premier sommet de la forme qu'on souhaite obtenir.
     * @param length - La longueur du premier segment de la forme qu'on souhaite obtenir.
     * @param rotation - L'angle de rotation desite (par rapport au point d'origine, en degres)
     * @return le {@link GeoPolygon} correspondant a la transformation demandee.
     */
    public static GeoPolygon transformShape(List<Point> shape, GeoPoint origin, Double length, Double rotation) {
        GeoPolygon transformedShape = new GeoPolygon();

        Double scale = null;
        Point previous = null;

        // Convertion du point geographique d'origine dans un repere carthesien classique
        Point lastComputed = fromLatLngToPoint(origin);

        for (Point currentPoint : shape) {
            // Pas besoin de faire de calcul pour le premier point, il est donne en parametre
            if (previous != null) {
                // Calcul du ratio de taille entre la forme enregistree et celle qui est demandee
                if (scale == null) {
                    double distance = distancePoints(currentPoint, previous);
                    scale = length / distance;
                }
                // Calcul de la position du sommet i: Pi = P(i-1) + (Si - S(i-1))*scale
                Point segment = subtractPoint(currentPoint, previous); // calcul de (Si - S(i-1))
                segment.multiply(scale); // multiplication par la valeur d'echelle calculee

                // decalage par rapport au point precedant
                lastComputed = addPoints(lastComputed, segment);

            }
            previous = currentPoint;

            // Conversion et ajout du point calcule dans le polygone
            transformedShape.addVertex(fromPointToLatLng(lastComputed));
        }

        // If necessary, rotate the polygon
        if (rotation != null) {
            transformedShape.rotate(rotation);
        }

        return transformedShape;
    }

    /**
     * Convertit un {@link GeoPoint} (latitude-longitude) dans un repere classique.
     *
     * @param latlng le {@link GeoPoint} a convertir.
     * @return le {@link Point} equivalent.
     */
    public static Point fromLatLngToPoint(GeoPoint latlng) {
        double x = (latlng.getLng() + 180) / 360 * 256;
        double y = ((1 - Math.log(Math.tan(latlng.getLat() * Math.PI / 180)
                + 1 / Math.cos(latlng.getLat() * Math.PI / 180)) / Math.PI) / 2 * Math.pow(2, 0)) * 256;
        return new Point(x, y);
    };

    /**
     * Projette un {@link Point} (x-y) sur la surface terrestre afin d'obtenir latitude et longitude.
     *
     * @param point le {@link Point} a convertir.
     * @return le {@link GeoPoint} equivalent.
     */
    public static GeoPoint fromPointToLatLng(Point point) {
        double lng = point.getX() / 256 * 360 - 180;
        double n = Math.PI - 2 * Math.PI * point.getY() / 256;
        double lat = (180 / Math.PI * Math.atan(0.5 * (Math.exp(n) - Math.exp(-n))));
        return new GeoPoint(lat, lng);
    };

    /**
     * Ajoute les coordonnes de deux points
     *
     * @param p1 - le premier {@link Point} a ajouter
     * @param p2 - le deuxieme {@link Point} a ajouter
     * @return le nouveau {@link Point} resultant de la somme des deux autres.
     */
    public static Point addPoints(Point p1, Point p2) {
        return new Point((p1.getX() + p2.getX()), (p1.getY() + p2.getY()));
    }

    /**
     * Soustrait les coordonnees d'en point a un autre
     *
     * @param p1 - le {@link Point} duquel on va soustraire
     * @param p2 - le {@link Point} a soustraire
     * @return le nouveau {@link Point} resultant de la difference des deux autres.
     */
    public static Point subtractPoint(Point p1, Point p2) {
        return new Point((p1.getX() - p2.getX()), (p1.getY() - p2.getY()));
    }

    /**
     * Calcule la distance entre deux points.
     * Formule: (x1-x2)^2 + (y1-y2)^2
     *
     * @param p1 - le premier {@link Point}
     * @param p2 - le deuxieme {@link Point}
     * @return la distance entre ces deux points.
     */
    public static double distancePoints(Point p1, Point p2) {
        return ((pow(p1.getX() - p2.getX(),2)) + (pow(p1.getY() - p2.getY(),2)));
    }
}
