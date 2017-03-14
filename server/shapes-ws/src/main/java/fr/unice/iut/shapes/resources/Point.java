package fr.unice.iut.shapes.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Point {

    private double x;

    private double y;

    // On a besoin d'un constructeur par defaut et des getter/setter pour la serialisation automatique en json/xml.
    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @XmlElement(name = "x")
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @XmlElement(name = "y")
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Mutiplie le vecteur par le scalaire donnne.
     *
     * @param d - le nombre par lequel on multiplie le point.
     */
    public void multiply(double d) {
        this.x *= d;
        this.y *= d;
    }

    /**
     * Effectue une rotation d'angle donne du point autour d'une origine.
     *
     * @param origin - le {@link Point} centre de la rotation
     * @param angle - l'angle de la rotation, en degres
     */
    public void rotate(Point origin, double angle) {
        double angleRad = angle * Math.PI / 180.0;
        double newX = Math.cos(angleRad) * (this.x - origin.x) - Math.sin(angleRad) * (this.y - origin.y) + origin.x;
        double newY = Math.sin(angleRad) * (this.x - origin.x) + Math.cos(angleRad) * (this.y - origin.y) + origin.y;
        this.x = newX;
        this.y = newY;
    }
}
