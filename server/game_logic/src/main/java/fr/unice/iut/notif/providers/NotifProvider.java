package fr.unice.iut.notif.providers;

import fr.unice.iut.notif.resources.Notif;
import fr.unice.iut.zone.persistance.ZonePersi;
import fr.unice.iut.zone.resources.Zone;
import fr.unice.iut.equipe.resources.Equipe;
/**
 * Created by Ismael on 10/12/2016.
 */


public class NotifProvider {

    public static String getMsgNotif(){
        ZonePersi sql = new ZonePersi();
        Notif notif = new Notif();
        int niveau = notif.getNiveau();
        int lvl = ++niveau;
        sql.setNiveau(String.valueOf(lvl),"IUT Sophia Antipolis");
        //int newlvl = sql.getNiveauZoneSql("IUT Sophia Antipolis");

        System.out.println(sql.getNiveauZoneSql("IUT Sophia Antipolis"));
        String message = notif.getMessage() + " Lvl " + String.valueOf(notif.getNiveau());
        return "[{\"notif\":\"" + message + "\"}]";
    }

    public static String getInfoWindow(){
      ZonePersi sql = new ZonePersi();
      String infoZone = sql.getInfoZone("IUT Sophia Antipolis");
      System.out.println(infoZone);
      return "[{\"informationZone\":\"" + infoZone + "\"}]";
    }

}
