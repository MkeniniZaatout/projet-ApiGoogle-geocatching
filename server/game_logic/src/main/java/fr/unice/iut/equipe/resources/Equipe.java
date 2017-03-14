package fr.unice.iut.equipe.resources;

import fr.unice.iut.joueur.resources.Joueur;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by ludov on 03/12/2016.
 */

@XmlRootElement
public class Equipe {

    private String nom;
    private List<Joueur> joueurs = new ArrayList<Joueur>();
    private String Couleur;


    public Equipe(){
        this.nom="NULL";
    }

    public Equipe(String nom, List<Joueur> joueurs, String couleur) {
        this.nom = nom;
        this.joueurs = joueurs;
        Couleur = couleur;
    }

    @XmlElement(name = "id")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    @XmlElement(name = "joueur")
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    @XmlElement(name = "couleur")
    public String getCouleur() {
        return Couleur;
    }

    public void setCouleur(String couleur) {
        Couleur = couleur;
    }
}
