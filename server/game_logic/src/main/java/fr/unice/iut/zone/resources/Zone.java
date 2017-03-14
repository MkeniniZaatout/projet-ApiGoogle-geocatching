package fr.unice.iut.zone.resources;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.lang.String;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import fr.unice.iut.equipe.resources.Equipe;
import fr.unice.iut.joueur.resources.Joueur;
import fr.unice.iut.zone.Observer.Observateur;
import fr.unice.iut.zone.Observer.Observable;
import fr.unice.iut.zone.persistance.ZonePersi;

@XmlRootElement
public class Zone implements Observable{

    ArrayList tabObservateur;// Tableau d'observateurs.
    private Equipe equipe;
    private String nom;
    private String shape;
    private int niveau;
    private int nbJoueurInZone;
	private static Map<String, Calendar> enhancementTimers  = new HashMap<String, Calendar>();
    public ZonePersi sql = new ZonePersi();

    public Zone(String nom, String shape) {
		this.shape = shape;
        this.niveau = 1;
        this.nbJoueurInZone = 0;
        this.equipe = null;
    }

    public Zone(String nom, String shape, Equipe equipe) {
        this.shape = shape;
        this.niveau = 1;
        this.nbJoueurInZone = 0;
        this.equipe = equipe;
    }
    public Zone(){
        this.niveau = 1;
        this.tabObservateur = new ArrayList();
    }

	public String getNomSql() {
        return "IUT Sophia Antipolis";
    }

    @XmlElement(name = "id")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement(name = "shape")
    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @XmlElement(name = "equipe")
    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

	@XmlElement(name = "niveau")
    public int getNiveau() {
        return niveau;
    }

    public int getNiveauSql() {
        return sql.getNiveauZoneSql("IUT Sophia Antipolis");
    }



    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @XmlTransient
    public int getNbJoueurInZone() {
        return nbJoueurInZone;
    }

    public void setNbJoueurInZone(int nbJoueurInZone) {
        this.nbJoueurInZone = nbJoueurInZone;
    }



    public ArrayList getTabObservateur() {
        return tabObservateur;
    }

    public void setTabObservateur(ArrayList tabObservateur) {
        this.tabObservateur = tabObservateur;
    }

    // Permet d'ajouter (abonner) un observateur de la zone.
    public void ajouterObservateur(Observateur o)
    {
        tabObservateur.add(o);
    }

    // Méthode permettant de notifier tous les observateurs lors d'un changement d'état de la zone.
    //Seul la class notif est observateur ici
    public void notifierObservateurs()
    {
        for(int i=0;i<tabObservateur.size();i++)
        {
            Observateur o = (Observateur) tabObservateur.get(i);
            o.actualiser(this);// On utilise la méthode "tiré".
        }
    }

    // This methode update level's Zone
    // UpgradeLevel check if player win Mini Game
    public void upgradeLevel(){
            ++niveau;
            int level = niveau;
            // Je notifie les observateurs
            // du changement d'etat de la zone ,
            notifierObservateurs();
            // Je upgrage le lvl de la zone dans la bd
            sql.setNiveau(String.valueOf(level),"IUT Sophia Antipolis");
    }

    // Permet de supprimer un observateur
    public void supprimerObservateur(Observateur o)
    {
        tabObservateur.remove(o);
    }


    public Map<String, Calendar> getEnhancementTimers() { return enhancementTimers; }

    public Calendar getEnhancementTimer(Joueur joueur) { return  enhancementTimers.get(joueur.getPseudo()); }

    public void setEnhancementTimers(Joueur joueur, Calendar calendar) { enhancementTimers.put(joueur.getPseudo(), calendar); }

}
