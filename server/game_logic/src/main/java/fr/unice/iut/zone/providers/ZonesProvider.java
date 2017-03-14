package fr.unice.iut.zone.providers;

import fr.unice.iut.equipe.providers.EquipesProvider;
import fr.unice.iut.equipe.resources.Equipe;

import fr.unice.iut.zone.resources.Zone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZonesProvider {



	private static Map<String, Zone> allZones = new HashMap<String, Zone>();

    public static List<Zone> getAllZones() {
        return new ArrayList<Zone>(allZones.values());
    }

    public static Zone findZoneById(String id) {
        return allZones.get(id);
    }

    // Classe utilitaire: on n'instancie pas
    private ZonesProvider() {
    }

    public static void createZone(Zone zone) throws Exception {
        if (findZoneById(zone.getNom()) != null) {
            throw new Exception();
        } else {
            allZones.put(zone.getNom(), zone);
        }
    }

    static {
        Zone zone1 = new Zone("IUT Sophia Antipolis","Rectangle");
        allZones.put(zone1.getNom(),zone1);

        Equipe equipe = EquipesProvider.findEquipeById("Team Trompette");
        Zone zone2 = new Zone("Nice","Carre",equipe);

        allZones.put(zone2.getNom(),zone2);
    }
}