package shape;

import fr.unice.iut.shapes.resources.GeoPoint;
import fr.unice.iut.shapes.resources.GeoPolygon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ludov on 04/12/2016.
 */
public class GeoPolygoneTest {

    private GeoPolygon geoPolygon;
    private GeoPoint geoPoint1 = new GeoPoint(7.5,2.3);
    private GeoPoint geoPoint2 = new GeoPoint(6,3);
    private GeoPoint geoPoint3 = new GeoPoint(8,2);

    @Before
    public void setup(){
        geoPolygon = new GeoPolygon();
        List<GeoPoint> vertices = new ArrayList<GeoPoint>();
        vertices.add(geoPoint1);
        vertices.add(geoPoint2);
        vertices.add(geoPoint3);
        geoPolygon.setVertices(vertices);
    }

    @Test
    public void testAddVertex(){
        GeoPolygon geoPolygoneCompare = new GeoPolygon();
        geoPolygoneCompare.addVertex(geoPoint1);
        geoPolygoneCompare.addVertex(geoPoint2);
        geoPolygoneCompare.addVertex(geoPoint3);
        assertArrayEquals(geoPolygon.getVertices().toArray(), geoPolygoneCompare.getVertices().toArray());
    }
}
