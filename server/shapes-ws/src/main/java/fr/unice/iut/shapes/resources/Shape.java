package fr.unice.iut.shapes.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Shape {

    private String id;
    private List<Point> points = new ArrayList<Point>(); //
    private int vertices;

    public Shape() {
    }

    public Shape(String id) {
        this.id = id;
    }

    @XmlElement(name = "id")
    public String getId() {
        return id;
    }

    @XmlElement(name = "vertices")
    public int getVertices() {
        return points.size();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    @XmlTransient
    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }


}

