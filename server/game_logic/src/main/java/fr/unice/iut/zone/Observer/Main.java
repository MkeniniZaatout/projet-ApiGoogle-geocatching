package fr.unice.iut.zone.Observer;
/**
 * Created by Ismael on 09/12/2016.
 */
import java.util.Scanner;

import fr.unice.iut.joueur.resources.Joueur;
import fr.unice.iut.zone.resources.Zone;
import fr.unice.iut.notif.resources.Notif;

public class Main {

	public static void main(String[] args){
		
		// Creation Zone
		Zone zone = new Zone();
		// Creation de l'observeur Notif
		Notif notif = new Notif();
		
		// On abonne l'observateur notif à Zone
		zone.ajouterObservateur(notif);
		// On simule le renforcement ou non de la zone
		Joueur joueur = new Joueur("Ismael", 7.58, 9.5);
		joueur.renforcerZone();
		zone.upgradeLevel();

		//zone.upgradeLevel(joueur);
	}
	
}
