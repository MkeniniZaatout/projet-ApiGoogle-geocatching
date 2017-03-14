package shape;

import fr.unice.iut.shapes.providers.ShapesProvider;
import fr.unice.iut.shapes.resources.Shape;
import fr.unice.iut.shapes.exception.ShapeAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by ludov on 04/12/2016.
 */
public class ShapesProvidersTest {

    Shape shape;
    Shape shape2;

    @Before
    public void setup() {
        shape = ShapesProvider.findShapeById("Carre");
        shape2 = ShapesProvider.findShapeById("Rectangle");
    }

    @Test
    public void testFindJoueurById() {
        Shape shapeCompare = ShapesProvider.findShapeById("Carre");
        assertEquals(shape, shapeCompare);
    }

    @Test
    public void testGetAllJoueurs() {
        /*
        List<Shape> shapes = new ArrayList<Shape>();
        shapes.add(shape);
        shapes.add(shape2);
        List<Shape> shapesCompare = ShapesProvider.getAllShapes();
        assertEquals(shapes.size(), shapesCompare.size());
        */
    }
}
