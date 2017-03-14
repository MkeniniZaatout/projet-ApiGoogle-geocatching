# Team Trompette

  ADLAL Mohamed-Mehdi  
  ROBEZ Ludovic  
  CHENE Emmanuel  
  MKENINI Ismael  

*Structure du Projet :*

* \geo-d-team_trompette
	* \client (contient le code du site web , page d'authentification , map ....) 
		* geocatching-Admin.html
		* geocatching.html
		* index.html
		* scenario-renforcement.txt
		* script.js
			* \Image
				* Dialog-close.png
			* \Sound
				* Renforcement-Zone!.mp3
			* \style
				* style.css
			* \script
				* box-renforce.js
				* md5-min.js
				* script.js
	* \server (contient le code java gérant la partie back-end/serveur, c'est a dire la partie qu'on ne voit pas à l'ecran)  
			* \authentification
			* \game-logic
			* \shape-ws
	* Diapo.pptx
	* Jenkinsfile
	* README.md
	.gitignore
**A faire pour lancer le projet :**

*Pre-requis :*
* Suivre ce tuto pour installer maven : http://www.mkyong.com/maven/how-to-install-maven-in-windows/  
* +    Installer aussi  [Node.js](https://nodejs.org) 
* +    Installer aussi bower : `npm install -g bower` 
* +    Installer aussi grunt : `npm install -g grunt-cli` 
* +	   Installer les modules bower : `bower install` 

*Ouvrir un terminal dans le dossier server pour taper les commandes suivantes*  
`mvn clean install` ; `mvn jetty:run`;  
-Exécuter si necessaire `grunt` pour la construction et `grunt serve` pour la prévisualisation : 
*    Installer les modules nodejs : `npm install`  
*    Installer les modules bower : `bower install`  
*    Installer les modules bower : `bower install`

*Le web-service est alors en marche,tapez sur votre navigateur web localhost:8080 et saisir son login*
*Vous accederez ainsi à la map.*

**Ce qui est fait**

*Afficher la box-dialog ( LPIAD-43 ) 
*Empêcher un joueur de renforcer à nouveau une zone ( LPIAD-36 )
*utiliser une BD ( LPIAD 50 )
*Déclenchement du renforcement d'une zone ( LPIAD 38)

*Pour intégrer la bd , suivre les instructions suivantes :* 
- intégrer le plugin mysql connector dans le pom.xml du dossier server : 
ajouter la dépendance : `<dependency><groupId>mysql</groupId> <artifactId>mysql-connector-java</artifactId> <version>5.1.4</version></dependency>` apres dependance de junit. 
- suivre le tutoriel suivant pour installer les élements nécéssaires pour avoir accès 
à une base de donnée : http://www-sop.inria.fr/members/Patrick.Itey/cours/jdbc/cnrs/tps/1.html  le mdp et login : licence06
