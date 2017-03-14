package equipe;

import fr.unice.iut.equipe.providers.EquipesProvider;
import fr.unice.iut.joueur.resources.Joueur;
import org.junit.Before;
import org.junit.Test;
import fr.unice.iut.equipe.resources.Equipe;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ludov on 04/12/2016.
 */
public class EquipesProviderTest {

    @Test
    public void testFindEquipeById() {
        Equipe equipeCompare = EquipesProvider.findEquipeById("Team Trompette");
        assertEquals("Team Trompette", equipeCompare.getNom());
    }

    @Test
    public void testGetAllEquipes() {
        List<Equipe> equipesCompare = EquipesProvider.getAllEquipes();
        assertEquals(2, equipesCompare.size());
    }
}
