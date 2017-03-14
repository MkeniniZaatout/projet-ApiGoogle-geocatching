
package fr.unice.iut.zone.Observer;
/**
 * Created by Ismael on 09/12/2016.
 */

import java.util.Scanner;
import fr.unice.iut.zone.resources.Zone;


// Interface implémentée par toutes les classes souhaitant avoir des observateurs à leur écoute.
public interface Observable
{
        // Méthode permettant d'ajouter (abonner) un observateur.
        public void ajouterObservateur(Observateur o);
        // Méthode permettant de supprimer (résilier) un observateur.
        public void supprimerObservateur(Observateur o);
        // Méthode qui permet d'avertir tous les observateurs lors d'un changement d'état.
        public void notifierObservateurs();
}