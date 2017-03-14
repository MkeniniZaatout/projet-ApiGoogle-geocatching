package joueur;

import fr.unice.iut.joueur.providers.JoueursProvider;
import fr.unice.iut.joueur.resources.Joueur;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ludov on 04/12/2016.
 */

public class JoueursProviderTest {

    @Test
    public void testFindJoueurById() {
        Joueur joueurCompare = JoueursProvider.findJoueurById("Jones");
        assertEquals("Jones", joueurCompare.getPseudo());
    }


    @Test
    public void testGetAllJoueurs() {
        List<Joueur> joueursCompare = JoueursProvider.getAllJoueurs();
        assertEquals(4, joueursCompare.size());
    }

}
