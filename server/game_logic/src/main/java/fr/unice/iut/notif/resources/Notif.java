package fr.unice.iut.notif.resources;

/**
 * Created by Ismael on 09/12/2016.
 */
import fr.unice.iut.equipe.resources.Equipe;
import fr.unice.iut.zone.Observer.Observable;
import fr.unice.iut.zone.Observer.Observateur;
import fr.unice.iut.zone.resources.Zone;
import fr.unice.iut.zone.persistance.ZonePersi;
import fr.unice.iut.equipe.providers.EquipesProvider;



public class Notif implements Observateur{

    private String message;
    private Zone zone;
    private int niveau;
    private ZonePersi sql;
    public Notif(){
        this.zone = new Zone();
        this.sql = new ZonePersi();
        this.message = "L'equipe " + sql.getNomEquipe() + " a renforce la zone " +  zone.getNomSql()   + "! :";
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public String getMessage() {
        return message;
    }

    public int getNiveau() {
        return zone.getNiveauSql();
    }

    // Méthode appelée automatiquement lors d'un changement d'état de la zone.
    public void actualiser(Observable o)
    {
        if(o instanceof Zone)
        {
            Zone z = (Zone) o;
            System.out.println(message);
        }

    }
}
