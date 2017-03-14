package fr.unice.iut.zone.Observer;
/**
 * Created by Ismael on 09/12/2016.
 */
import java.util.Scanner;
import fr.unice.iut.zone.resources.Zone;
import java.lang.*;

public class Notif implements Observateur {
    // Méthode appelée automatiquement lors d'un changement d'état de la zone.
    private String message;

    Notif(){
        this.message = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void actualiser(Observable o)
    {
            if(o instanceof Zone)
            {       
                    Zone z = (Zone) o;

                    String message = "L'équipe à renforcer la zone ! Level de la zone : " + z.getNiveau();
                    setMessage(message);
                    System.out.println(message);
            }

    }


}
