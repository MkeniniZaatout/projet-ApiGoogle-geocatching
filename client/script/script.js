/** HTTP REQUESTS **/
function httpGetAsync(url, status, successCallback, failureCallback) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == status) {
                successCallback(xmlHttp.responseText);
            } else {
                failureCallback(xmlHttp.status, xmlHttp.responseText);
            }
        }
    }
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}


function httpDeleteAsync(url, status, successCallback, failureCallback) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == status) {
                successCallback(xmlHttp.responseText);
            } else {
                failureCallback(xmlHttp.status, xmlHttp.responseText);
            }
        }
    }
    xmlHttp.open("DELETE", url, true);
    xmlHttp.send(null);
}

function httpPostAsync(url, param, status, successCallback, failureCallback) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == status) {
                successCallback(xmlHttp.responseText);
            } else {
                failureCallback(xmlHttp.status, xmlHttp.responseText);
            }
        }
    }
    var json = JSON.stringify(param);
    xmlHttp.open("POST", url, true);
    xmlHttp.setRequestHeader("Content-Type","application/json");
    xmlHttp.send(json);
}

/** NOTIF **/
function Notif(url, Key){
var notif;
$.getJSON(url, function( data ){
		$.each( data, function(key , value){
			notif =  value[Key];
			alert(notif);
		});// each
	});// getJson
}


/** COOKIE **/

function setCookie(name, value) {
    var today = new Date(), expires = new Date();
    expires.setTime(today.getTime() + (365*24*60*60*1000));
    document.cookie = name + "=" + encodeURIComponent(value) + ";expires=" + expires.toGMTString();
}

function getCookie(name) {
    var cookContent = document.cookie, cookEnd, i, j;
    var name = name + "=";
    for (i=0, c=cookContent.length; i<c; i++) {
        j = i + name.length;
        if (cookContent.substring(i, j) == name) {
            cookEnd = cookContent.indexOf(";", j);
            if (cookEnd == -1)
                cookEnd = cookContent.length;
            return decodeURIComponent(cookContent.substring(j, cookEnd));
        }
    }
    return null;
}

/** CHECK TOKEN **/
function check() {
	var url = "http://iut-outils-gl.i3s.unice.fr/jetty/authentication/session";
	var token = getCookie("token");
	httpPostAsync(url, token, 204, function(res) {

	}, function (status, error) {
		alert("Wrong token");
		document.location.href="index.html";
	});
}


function checkToken(){
    var url = window.location.search;
    var token = url.substring(url.lastIndexOf("?")+1);
    $.ajax({
        type: "POST",
        url: 'http://iut-outils-gl.i3s.unice.fr/jetty/authentication/session',
        headers : { 'Content-Type':'application/x-www-form-urlencoded' },
        data: token,
        dataType : 'json', // Ton serveur rejourne du JSON, autant demander à jQuery de le parser
        statusCode: {
            401: function(xhr) {
                var url = "index.html";
                window.location = url;
            }
        }//StatusCode
    });//ajax
};

/** MAP RELATED **/

var map;
var tabpolygon = [];
var numpolygon = 0;

/* Draw a polygon with the given vertices and color. */
function drawPolygon(vertices) {
    var polygon = new google.maps.Polygon({
        paths: vertices,
		    strokeColor: "#FFFFFF",
        strokeOpacity: 0.8,
        strokeWeight: 2,
		fillColor: "#FFFFFF",
        fillOpacity: 0.35
    });
    tabpolygon[numpolygon] = polygon;
    numpolygon++;
	polygon.addListener('click', showArrays);
	infoWindow = new google.maps.InfoWindow;
}

// fct d'EMANUELLE permettant d'afficher l'infoWindow
function showArrays(event) {
  $.getJSON("http://localhost:8080/game_logic/rest/notif/infoWindow", function( data ){
  		$.each( data, function(key , value){
  			var informationZone = value['informationZone'];
        infoWindow.setContent(informationZone);
        infoWindow.setPosition(event.latLng);
        infoWindow.open(map);
  		});// each
  	});// getJson
  }



/** SHAPES WS - RELATED **/

/* Retrieves the vertices for a given polygon from the WS and displays it. */
function getShape(id, lat, lng, length, rot) {
    var url = "http://localhost:8080/shapes-ws/rest/shapes/" + id + "?lat=" + lat + "&lng=" + lng + "&length=" + length;
    if (rot !== 0) {
        url += "&rot=" + rot;
    }
    httpGetAsync(url, 200, function(res) {
        var json = JSON.parse(res);
        //alert(res);
        drawPolygon(json.vertices);
    }, function (status, error) {
           errorStatus(status, error);
    });
}

/** Creation de zone a partir du service de Shape **/
function createZones(shapeId) {
    var url = "http://localhost:8080/game_logic/rest/notif/" + shapeId
    httpGetAsync(url, 200, function(res) {
        var json = JSON.parse(res);
        drawPolygon(json[0]);
    }, function (status, error) {
           errorStatus(status, error);
    });
}

/* Fonction pour la gestion de point de niveau de la zone */
function enouthPoint() {
    var url = "http://localhost:8080/game_logic/rest/Joueurs";
    httpGetAsync(url, 200, function(res) {
              return res;
    }, function(status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}

/* Fonction pour la gestion de point de point de niveau de la zone */
function enouthPoint(id, niv) {
    var url = "http://localhost:8080/game_logic/rest/joueurs/" + id + "?NiveauZone=" + niv;
    httpGetAsync(url, 200, function(res) {
              return res;
    }, function(status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}

var marker;
/* Display the map and calls the WS */
function initMap() {

    checkToken();
	// Create the map
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 120,
        center: {lat: 43.70664325546818, lng: 7.262647440663783},
        mapTypeId: google.maps.MapTypeId.TERRAIN
    });

	// Creation d'une zone sur Nice ville
  createZones("rectangle");
	/* Lancer le bouton de renforcement de zone */
	var gamediv = document.createElement('div');
	new GameLaunch(gamediv, map);
	map.controls[google.maps.ControlPosition.TOP_CENTER].push(gamediv);
	gamediv.style.display = "none";//"inline";

  /* Function Du groupe F pour le boutton renforcement */
  function GameLaunch(controlDiv, map) {
      var controlUI = document.createElement('div');
      CustomControl(controlDiv,controlUI,'Renforcer la zone');
      controlUI.addEventListener('click', function() {
        // Lancer Renforcement de Zone
        Notif("http://localhost:8080/game_logic/rest/notif","notif");
        gamediv.style.display = "none";
        // timer
        function myFunction(){
          gamediv.style.display = "initial";
          // Emettre un sons
          // Afin d'avertir
          var s = 'Sound/Hey.mp3';
          var e = document.createElement('audio');
          e.setAttribute('src',s);
          e.play();
        }
        setTimeout(myFunction, 25000)
      });
  }

  /* Function Du groupe F pour le boutton renforcement */
  	function CustomControl(controlDiv, controlUI, inText) {
  	controlUI.style.backgroundColor = '#dd0b0b';
      controlUI.style.border = '2px solid #ffffff';
      controlUI.style.borderRadius = '5px';
      controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
      controlUI.style.cursor = 'pointer';
      controlUI.style.marginBottom = '22px';
      controlUI.style.textAlign = 'center';
      controlDiv.appendChild(controlUI);
      var controlText = document.createElement('div');
      controlText.style.color = 'rgb(25,25,25)';
      controlText.style.fontFamily = 'Tohoma';
      controlText.style.fontSize = '30px';
      controlText.style.lineHeight = '40px';
      controlText.style.paddingLeft = '10px';
      controlText.style.paddingRight = '10px';
      controlText.innerHTML = inText;
      controlUI.appendChild(controlText);
  }
  var positionDebutTest= {
      lat:43.70600823549277,
      lng:7.2569310665130615
  };

  var positionDansLaZone ={
        lat: 43.71191167260603,
        lng: 7.271661758422852
    };
     marker = new google.maps.Marker({map: map, position: positionDebutTest});

    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(function(position) {

	        var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
          var currentpt = new google.maps.LatLng(pos.lat,pos.lng);

        // Lancement du test pour la teleportation de la demo
      			$('#teleportation').click(function (){
      			//marker2 = new google.maps.Marker({map: map});
      			marker.setPosition(positionDansLaZone);
            map.setCenter(positionDansLaZone);
      			gamediv.style.display = "inline";
      			});

      // Set marker's position
      //marker.setPosition(positionDebutTest);
      map.setCenter(positionDebutTest);

			// Check if a point is into a polygon
      for (i in tabpolygon) {
          couleur = "#009999";
				if(google.maps.geometry.poly.containsLocation(currentpt, tabpolygon[i])) {
          tabpolygon[i].fillColor = '#ff3300';
					tabpolygon[i].strokeColor = '#ff3300';
          tabpolygon[i].setMap(map);
                }
                else {
                    tabpolygon[i].fillColor = "#ff3300";
					          tabpolygon[i].strokeColor = "#ff3300";
                    tabpolygon[i].setMap(map);
                }
            }
        }, function() {
               handleLocationError(true, infoWindow, map.getCenter());
        });
    }
	else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }
}


/* Case error Geolocation. */
function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
                        'Error: The Geolocation service failed.' :
                        'Error: Your browser doesn\'t support geolocation.');
}

/* Case error HTTP Requests. */
function errorStatus(status, error) {
    console.log("Failure: (" + status + ": " + error + ")");
}


/////////////////////////////////// FONCTION DE LUDOVIC ////////////////////////////////////////////
function renforcerZone(id, nivDiff) {
    var url = "http://localhost:8080/game_logic/rest/zone/" + id + "?niveauDifficulté=" + nivDiff;
	httpGetAsync(url, 200, function(res) {
    }, function (status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}

function getZone(id, lat, lng, length, rot) {
    var url = "http://localhost:8080/game_logic/rest/zone/" + id + "?lat=" + lat + "&lng=" + lng + "&length=" + length;
    if (rot !== 0) {
        url += "&rot=" + rot;
    }
    httpGetAsync(url, 200, function(res) {
        try {
            var jsonZone = JSON.parse(res);
            drawPolygon(jsonZone.id, jsonZone.vertices, jsonZone.color);
            if (res !== expected) {
                throw "invalid response from ws";
            }
        } catch(e) {
            alert("error : " + e);
        }
    }, function (status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}
function getAllZones() {
    var url = "http://localhost:8080/game_logic/rest/zone";
    httpGetAsync(url, 200, function(res) {
             return JSON.parse(res)
    }, function(status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}
function enouthPoint(id, niveau) {
    var url = "http://localhost:8080/game_logic/rest/joueurs/" + id + "?NiveauZone="+ niveau;
    httpGetAsync(url, 200, function(res) {
              return res;
    }, function(status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}

function getJoueur (id){
	    var url = "http://localhost:8080/game_logic/rest/joueurs/" + id;
    httpGetAsync(url, 200, function(res) {
              return JSON.parse(res);
    }, function(status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}

function getAllJoueurs (){
	var url = "http://localhost:8080/game_logic/rest/joueurs";
	    httpGetAsync(url, 200, function(res) {
             return JSON.parse(res)
    }, function(status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}

function getEquipe(id){
		    var url = "http://localhost:8080/game_logic/rest/equipes/" + id;
    httpGetAsync(url, 200, function(res) {
              return JSON.parse(res);
    }, function(status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}

function getAllEquipes(){
	 var url = "http://localhost:8080/game_logic/rest/equipes";
		    httpGetAsync(url, 200, function(res) {
             return JSON.parse(res)
    }, function(status, error) {
        alert("status : "+ status +" / error : " + error);
        var url = "index.html";
        //window.location = url;
    });
}


function createZone(id, shapeId)
{
	var json = JSON.parse('{"id":"' & id & '","shape"' & shapeId & '"}');
	var url = "http://localhost:8080/game_logic/rest/zone";
	httpPostAsync(url, json, 201, function(res) {

    }, function (status, error) {
		alert("Code "+ status +" error : " + error);
		var url = "index.html";
		//window.location = url;
  });
}
