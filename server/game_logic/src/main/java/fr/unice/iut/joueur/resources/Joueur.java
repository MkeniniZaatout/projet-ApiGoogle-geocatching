package fr.unice.iut.joueur.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import fr.unice.iut.zone.resources.Zone;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.Scanner;


/**
 * Created by ludov on 03/12/2016.
 */

public class Joueur {

    private String pseudo;
    private double lat;
    private double lng;
    private int point;

    public Joueur(String pseudo, double lat, double lng) {
        this.pseudo = pseudo;
        this.lat = lat;
        this.lng = lng;
        this.point = 0;
    }

    public Joueur(String pseudo, double lat, double lng, int point) {
        this.pseudo = pseudo;
        this.lat = lat;
        this.lng = lng;
        this.point = point;
    }

    public void renforcerZone(){
        Zone z = new Zone();
        System.out.println("Niveau actuel de la Zone : " + " level " + z.getNiveau());
        System.out.println("Simulation du mini Jeux : Combient font 4 X 4 ?");
        // Scanner : Saisie dans la console
        Scanner saisie = new Scanner(System.in);
        // lis l'entier saisie
        int n = saisie.nextInt();
        if(n == 24){
            System.out.println("Reponse Juste !");
            boolean succes = true;
            //setResultat(succes);
        }else{
            System.out.println("Reponse fausse !");
            boolean echec = false;
            //setResultat(echec);
        }
    }

    @XmlElement(name = "lat")
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }


    @XmlElement(name = "point")
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }


    @XmlElement(name = "lng")
    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


    @XmlElement(name = "id")
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

}
