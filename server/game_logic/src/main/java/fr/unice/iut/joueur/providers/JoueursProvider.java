package fr.unice.iut.joueur.providers;

import fr.unice.iut.equipe.resources.Equipe;
import fr.unice.iut.joueur.resources.Joueur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by ludov on 03/12/2016.
 */
public class JoueursProvider {

    private static Map<String, Joueur> allJoueurs = new HashMap<String, Joueur>();

    public static List<Joueur> getAllJoueurs() {
        return new ArrayList<Joueur>(allJoueurs.values());
    }

    public static Joueur findJoueurById(String id) {
        return allJoueurs.get(id);
    }
    // Classe utilitaire: on n'instancie pas
    private JoueursProvider() {
    }

    public static void createJoueur(Joueur joueur) throws Exception {
        if (findJoueurById(joueur.getPseudo()) != null) {
            throw new Exception();
        } else {
            allJoueurs.put(joueur.getPseudo(), joueur);
        }
    }

    static {
        Joueur joueur1 = new Joueur("Jones", 1, 1.5);
        allJoueurs.put(joueur1.getPseudo(), joueur1);

        Joueur joueur2 = new Joueur("Ismael", 7.58, 9.5);
        allJoueurs.put(joueur2.getPseudo(), joueur2);

        Joueur joueur3 = new Joueur("Rosalie", 10.5, 9.1);
        allJoueurs.put(joueur3.getPseudo(), joueur3);

        Joueur joueur4 = new Joueur("Lea", 7.7, 6.5);
        allJoueurs.put(joueur4.getPseudo(), joueur4);
    }
}
