package resources;

import fr.unice.iut.shapes.providers.ShapesProvider;
import fr.unice.iut.shapes.resources.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PointTest {
    private static final double delta=0.0;
    private Point point;
    private Shape shape;

    //Attributes before launch test
    @Before
    public void setup(){
        point = new Point(2.0,3.0);
        shape = new Shape("test");
    }

    //Method test of the default constructor from the class Point
    @Test
    public void testDefaultConstructor(){
        Point point = new Point();

        assertEquals(point.getX(),0, delta);
        assertEquals(point.getY(),0, delta);
    }

    //Method test of the constructor from the class Point
    @Test
    public void testConstructor(){
        assertEquals(point.getX(),2.0, delta);
        assertEquals(point.getY(),3.0, delta);
    }

    //Method test of setters from the class Point
    @Test
    public void testSetters(){
        Point point = new Point();

        point.setX(2.0);
        point.setY(3.0);

        assertEquals(point.getX(),2.0, delta);
        assertEquals(point.getY(),3.0, delta);
    }
	
    /*Method test of addPointToShape from the class Point
    @Test
    public void testAddPointToShape(){
        shape = ShapesProvider.findShapeById("star");
        int nb = shape.getNbPoint();
        shape.addPoint(new Point(4.2,2.6));
        assertEquals(nb+1, shape.getNbPoint());
    }*/

	
    //Method test of rotate from the class Point
    @Test
    public void testRotate360(){
        Point origin = new Point(0,0);

        Point startPoint = point;
        point.rotate(origin, 360);

        assertEquals(point.getX(), startPoint.getX(), delta);
        assertEquals(point.getY(), startPoint.getY(), delta);
    }

}
