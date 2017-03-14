package fr.unice.iut.equipe.providers;

import fr.unice.iut.equipe.resources.Equipe;
import fr.unice.iut.joueur.providers.JoueursProvider;
import fr.unice.iut.joueur.resources.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by ludov on 03/12/2016.
 */
public class EquipesProvider {

    private static Map<String, Equipe> allEquipes = new HashMap<String, Equipe>();

    public static List<Equipe> getAllEquipes() {
        return new ArrayList<Equipe>(allEquipes.values());
    }

    public static Equipe findEquipeById(String id) {
        return allEquipes.get(id);
    }
    // Classe utilitaire: on n'instancie pas
    private EquipesProvider() {
    }

    public static void createEquipe(Equipe equipe) throws Exception {
        if (findEquipeById(equipe.getNom()) != null) {
            throw new Exception();
        } else {
            allEquipes.put(equipe.getNom(), equipe);
        }
    }

    static {
        List<Joueur> joueurs1 = new ArrayList<Joueur>();
        joueurs1.add(JoueursProvider.findJoueurById("Ismael"));
        Equipe equipe1 = new Equipe("Team Trompette",joueurs1,"red");
        allEquipes.put(equipe1.getNom(),equipe1);

        List<Joueur> joueurs2 = new ArrayList<Joueur>();
        joueurs2.add(JoueursProvider.findJoueurById("Lea"));
        joueurs2.add(JoueursProvider.findJoueurById("Jones"));
        joueurs2.add(JoueursProvider.findJoueurById("Rosalie"));
        Equipe equipe2 = new Equipe("Team GoTo CoffeeMachine",joueurs2,"green");
        allEquipes.put(equipe2.getNom(),equipe2);
    }
}
