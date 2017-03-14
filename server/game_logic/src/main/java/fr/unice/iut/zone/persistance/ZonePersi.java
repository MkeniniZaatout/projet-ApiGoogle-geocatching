package fr.unice.iut.zone.persistance;

/**
 * Created by Ismael on 11/12/2016.
 */
 import java.io.ObjectInputStream.GetField;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import com.mysql.jdbc.ResultSetImpl;
 import java.sql.SQLException;
 import java.util.ArrayList;

 import com.mysql.jdbc.Connection;
 import com.mysql.jdbc.Statement;

import javax.sql.RowSet;

public class ZonePersi {

    public ZonePersi() {}

    //  Requete sql afin d'augmenter/diminuer le niveau de la zone
    // dans la table areas
    public static void setNiveau(String lvl,String nomZone) {
        //Acces à la BD
        String url = "jdbc:mysql://localhost/geocatching";
        String login = "root";
        String mdp = "root";
        Connection cn = null;
        Statement st = null;

        try {
            //Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            //récupération de la connexion
            cn = (Connection) DriverManager.getConnection(url, login, mdp);
            st = (Statement) cn.createStatement();
            //execution de la requete sql
            String sql = "UPDATE `areas` SET `z_niveau`=" + lvl  + " WHERE `z_nom`='" + nomZone + "'";
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            try {
                //deconnexion avec la db
                //liberer la memoire
                cn.close();
                st.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }//catch
        }//finally
    }//setniveau


	 // Creation de la zone
    public static void setZone(String nomZone,String Lat,String Lng,String Rot){
    	//Acces à la BD
        String url = "jdbc:mysql://localhost/geocatching";
        String login = "root";
        String mdp = "root";
        Connection cn = null;
        Statement st = null;
        try {
        	//Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            //récupération de la connexion
            cn = (Connection) DriverManager.getConnection(url, login, mdp);
            st = (Statement) cn.createStatement();
        	//execution de la requete sql
        	//String sql = "UPDATE `areas` SET `z_lat`=" + Lat + ",`z_lng`=" + Lng + ",`z_rot`=" + Rot + " WHERE `z_nom`='" + nomZone + "'";
            String sql = "INSERT INTO `areas`(`z_lat`, `z_lng`, `z_rot`,`z_nom`, `z_id_forme`) VALUES ("+Lat+","+Lng+","+Rot+",'"+nomZone+"','rectangle')";
            st.execute(sql);
        }catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                //deconnexion avec la db
                //liberer la memoire
                cn.close();
                st.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }//catch
        }//finally
    }//setLatlngRot

	 // recuperer le nom de l'equipe qui a capturé la zone.
    public static String getNomEquipe(){
    	// Acces à la BD
        String url = "jdbc:mysql://localhost/geocatching";
        String login = "root";
        String mdp = "root";
        Connection cn = null;
        Statement st = null;
        // Array List afin de récuperer le nom de l'équipe
        // qui a capturé la zone
        // à l'exterieur de la boucle
        ArrayList<String> nomEquipe = new ArrayList<String>();
        try {

        	//Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            //récupération de la connexion
            cn = (Connection) DriverManager.getConnection(url, login, mdp);
            st = (Statement) cn.createStatement();
        	// requete pour récuperer le nom d'equipe quand à capturé
            String sql = "SELECT `e_nom` FROM `equipes` INNER JOIN `areas` ON `equipes`.`e_id`=`areas`.`z_fk_e_id`";
            ResultSet resultat = st.executeQuery(sql);
            while(resultat.next()){
            	nomEquipe.add(resultat.getString(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {

            try {
                //deconnexion avec la db
                //liberer la memoire
                cn.close();
                st.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }// finally
        return nomEquipe.get(0);
    }// getNomEquipe

	// Requete afin recuperer le niveau de la zone
    // dans la table areas
    public static int getNiveauZoneSql(String nomZone) {
        //Acces à la BD
        String url = "jdbc:mysql://localhost/geocatching";
        String login = "root";
        String mdp = "root";
        Connection cn = null;
        Statement st = null;

        // Array List afin de récuperer le niveau de la zone
        // à l'exterieur de la boucle
        ArrayList<Integer> niveau = new ArrayList<Integer>();

        try {
            //Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            //récupération de la connexion
            cn = (Connection) DriverManager.getConnection(url, login, mdp);
            st = (Statement) cn.createStatement();
            //execution de la requete sql
            String sql = "SELECT `z_niveau` FROM `areas` WHERE `z_nom` ='" + nomZone +"'";
            ResultSet resultat = st.executeQuery(sql);
            while(resultat.next()){
                niveau.add(resultat.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {

            try {
                //deconnexion avec la db
                //liberer la memoire
                cn.close();
                st.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }
        //retourne le niveau de la zone
        return niveau.get(0);
    }//getNiveau

	 public static String getInfoZone(String nomZone){
        int lvl = getNiveauZoneSql(nomZone);
        String nomEquipe = getNomEquipe();
        return "La zone "+nomZone+" a ete capture par l'equipe "+nomEquipe+".</br>Son niveau actuel est "+Integer.toString(lvl)+ " ";
    }
	/*
    public static void setNom(String nom){
    }

*/

}
