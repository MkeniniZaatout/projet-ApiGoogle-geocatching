package fr.unice.iut.shapes.providers;

import fr.unice.iut.shapes.exception.ShapeAlreadyExistException;
import fr.unice.iut.shapes.resources.Point;
import fr.unice.iut.shapes.resources.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe charger de gerer la liste des formes mises a disposition par le service.
 * Ajout - Suppression - Requetage.
 */
public class ShapesProvider {

    /*
     * Map contenant l'ensemble des formes connues par le service.
     * Dans un service complet, il faudrait mettre en place un systeme de persistance (base de donnees, ...)
     */
    private static Map<String, Shape> allShapes = new HashMap<String, Shape>(); //Shape c'est une instance d'une classe qui s'appelle Shape

    public static List<Shape> getAllShapes() {
        return new ArrayList<Shape>(allShapes.values());
    }

    /**
     * Definit une nouvelle {@link Shape} dans le service.
     *
     * @param shape - L'objet {@link Shape} a ajouter
     * @throws ShapeAlreadyExistException si une forme avec le meme identifiant etait deja presente.
     */
    public static void createShape(Shape shape) throws ShapeAlreadyExistException {
        if (findShapeById(shape.getId()) != null) {
            throw new ShapeAlreadyExistException();
        } else {
            allShapes.put(shape.getId(), shape);
        }
    }

    /**
     * Supprime une {@link Shape} de la liste des formes mises a disposition par le service.
     *
     * @param shape - L'objet {@link Shape} a supprimer
     * @return true si la forme a bien ete supprime, false sinon (elle n'etait pas definie)
     */
    public static boolean deleteShape(Shape shape) {
        return allShapes.remove(shape.getId()) != null;
    }

    /**
     * Cherche dans les formes disponibles celle qui correspond a l'identifiant donne.
     *
     * @param id - L'identifiant de l'objet {{@link Shape}}
     * @return la forme, ou null s'il n'existe pas de forme avec cet identifiant.
     */
    public static Shape findShapeById(String id) {
        return allShapes.get(id);
    }

    // Classe utilitaire: on n'instancie pas
    private ShapesProvider() {
    }

    // Definition de quelques formes de base
    static {
        Shape carre = new Shape("Carre");
        carre.addPoint(new Point(133.2622, 93.0866));
        carre.addPoint(new Point(133.2622, 70));
        carre.addPoint(new Point(110, 70));
        carre.addPoint(new Point(110, 93.0866));
        allShapes.put(carre.getId(), carre);

        Shape shape = new Shape("rectangle");
        shape.addPoint(new Point(133.2622, 93.0866));
        shape.addPoint(new Point(133.2622, 70));
        shape.addPoint(new Point(110, 70));
        shape.addPoint(new Point(110, 93.0866));
        allShapes.put(shape.getId(), shape);

        shape = new Shape("rectangle2");
        shape.addPoint(new Point(133.2622, 93.0866));
        shape.addPoint(new Point(133.2622, 70));
        shape.addPoint(new Point(110, 70));
        shape.addPoint(new Point(110, 93.0866));
        allShapes.put(shape.getId(), shape);

    }
}