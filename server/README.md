# Team Trompette

  ADLAL Mohamed-Mehdi  
  ROBEZ Ludovic  
  CHENE Emmanuel  
  MKENINI Ismael  

*Structure du Projet :*

* \geo-d-team_trompette
	* \client (contient le code du site web , page d'authentification , map ....)  
		* geocatching.html
		* index.html
		* \script
			* md5-min.js
			* script.js
	* \server (contient le code java gérant la partie back-end/serveur, c'est a dire la partie qu'on ne voit pas à l'ecran)  
		* \authentification
		* \game-logic
		* \shape-ws

**A fair pour lancer le projet :** 

*Ouvrir un terminal bash dans le dossier server pour taper les commandes suivantes*  
`mvn clean install` ; `mvn jetty:run`

