package fr.unice.iut.zone.Observer;
/**
 * Created by Ismael on 09/12/2016.
 */
import java.util.Scanner;
import fr.unice.iut.zone.resources.Zone;


// Interface implémentée par tous les observateurs.
public interface Observateur
{
        // Méthode appelée automatiquement lorsque l'état (position ou précision) du GPS change.
        public void actualiser(Observable o);
}
