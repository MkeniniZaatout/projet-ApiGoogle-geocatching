package fr.unice.iut.zone.providers;

import fr.unice.iut.equipe.resources.Equipe;
import fr.unice.iut.zone.resources.Zone;

public class ZoneCalculator {


   public static void capturer(Zone zone) {
   }

   public static void renforcer(Zone zone, int niveauDifficulte)
   {
       zone.setNiveau(zone.getNiveau() + niveauDifficulte);
   }

    public static void renforcer(Zone zone)
    {
        zone.setNiveau(zone.getNiveau() + zone.getNbJoueurInZone());
    }

    public static void capturer(Zone zone,Equipe equipe) {
        if (zone.getNiveau() <= zone.getNbJoueurInZone()) {
            zone.setNiveau(zone.getNiveau() - zone.getNbJoueurInZone());
        } else {
            zone.setEquipe(equipe);
        }
    }


}

