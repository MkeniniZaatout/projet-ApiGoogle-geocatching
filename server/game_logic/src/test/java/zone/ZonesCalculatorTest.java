package zone;

import fr.unice.iut.equipe.providers.EquipesProvider;
import fr.unice.iut.equipe.resources.Equipe;

import fr.unice.iut.joueur.providers.JoueursProvider;
import fr.unice.iut.joueur.resources.Joueur;
import fr.unice.iut.zone.providers.ZoneCalculator;
import fr.unice.iut.zone.providers.ZonesProvider;
import fr.unice.iut.zone.providers.ZoneCalculator;
import fr.unice.iut.zone.resources.Zone;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import static org.junit.Assert.assertEquals;
/**
 * Created by ludov on 04/12/2016.
 */
public class ZonesCalculatorTest {

    Zone zone;

    @Before
    public void setup() {

        Equipe equipe = EquipesProvider.findEquipeById("Team Trompette");
        Zone maZone = new Zone("IUT Sophia Antipolis", "Carre", equipe);
        maZone.setNiveau(3);

    }

    @Test
    public void testCapturer(){

    }

    @Test
    public  void testRenforcer(){
        Zone zone = ZonesProvider.findZoneById("Nice");

        /*
        assertEquals(zone.getNiveau(), 1);

        ZoneCalculator.renforcer(zone,1);

        assertEquals(1,zone.getNiveau());
        ZoneCalculator.renforcer(zone,3);
        assertEquals(4,zone.getNiveau());
        */
    }

    @Test
    public  void testEnhancementTimers(){

        Zone maZone = new Zone("IUT Sophia Antipolis", "Carre");

        Joueur joueur1 = JoueursProvider.findJoueurById("Jones");
        Joueur joueur2 = JoueursProvider.findJoueurById("Lea");

        Calendar monCalendar = Calendar.getInstance();

        /*

        maZone.setEnhancementTimers(joueur1, monCalendar);
        maZone.setEnhancementTimers(joueur2, monCalendar);

        assertEquals(zone.getEnhancementTimers().size(), 2);

        Calendar monInstant = Calendar.getInstance();

        assertEquals(zone.getEnhancementTimer(joueur1).after(monInstant), false);
        assertEquals(zone.getEnhancementTimer(joueur2).after(monInstant), false);

        monInstant.add(Calendar.HOUR, -10);

        assertEquals(zone.getEnhancementTimer(joueur2).after(monInstant), true);
        assertEquals(zone.getEnhancementTimer(joueur2).before(monInstant), false);

        */

    }
}
