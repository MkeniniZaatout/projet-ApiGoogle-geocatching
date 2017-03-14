window.alert = function(text, aname)
{
    // Creation de la boite
    var div = document.createElement("div");
    var box = document.body.appendChild(div);
    box.setAttribute("class", "alertBox");
    box.style.display = "none";


	// Creation titre
    var span = document.createElement("span");
    var title = box.appendChild(span);
    title.setAttribute("class", "alertTitle");


	  // Emettre un sons de succes
	  var s = 'Sound/Renforcement-Zone!.mp3';
	  var e = document.createElement('audio');
	  e.setAttribute('src',s);
	  e.play();

	// Bouton fermant
    var img = document.createElement("img");
    var close = box.appendChild(img);
    close.src = "Image/dialog-close.png";
    close.width = 26;
    close.onclick = function()
    {
        document.body.removeChild(box);
		e.pause();
	}


    // Creation du contenu
    span = document.createElement("span");
    var content = box.appendChild(span);
    content.setAttribute("class", "alertContent");

    // Insertion contenus
    if(typeof(aname) == "undefined")
        aname = " Evenement "
    title.innerHTML = aname;
    content.innerHTML = text;

    // Definition des style
    var width = 435; // largeur
    var height = [120, 180]; // hauteur [min, max]
    box.style.position = "absolute";
    box.style.zIndex = 9998;
    box.style.overflox = "hidden";
    box.style.left = "50%";
    box.style.top = "50%";
    box.style.width = width + "px";
    box.style.minHeight = height[0] + "px";
    box.style.maxHeight = height[1] + "px";
    box.style.marginLeft = -(width / 2) + "px";
    box.style.marginTop = -(box.offsetHeight / 2) + "px";
    box.style.border = "2px #000000 solid";
    box.style.backgroundColor = "#BADFD4";
    box.style.font = "bold 30px arial,serif";
	box.style.color = "#183152";

    // CSS3
    box.style.borderTopRadius = "15px";
    box.style.borderBottomRadius = "5px";
    box.style.MozBorderRadius = "10px";
    box.style.boxShadow = "2px 2px 5px #000000";
    box.style.MozBoxShadow = "2px 2px 12px #000000";

    close.style.position = "absolute";
    close.style.cursor = "pointer";
    close.style.zIndex = 9999;
    close.style.right = "2px";
    close.style.top = "2px";

    title.style.display = "block";
    title.style.width = "100%";
    title.style.height = "32px";
    title.style.lineHeight = "32px";
    title.style.textAlign = "center";
    title.style.backgroundColor = "#000000";
    title.style.color = "#FFFFFF";
    title.style.fontWeight = "bold";
    // CSS3
    box.style.borderRadius = "5px";
    box.style.MozBorderRadius = "5px";

    content.style.display = "block";
    content.style.width = (width - 8) + "px";
    content.style.minHeight = (height[0] - 40) + "px";
    content.style.margin = "4px";
    content.style.fontSize = "14px";
    content.style.overflow = "auto";
	box.style.backgroundImage = "url('Image/Fond.png')";
    // Affichage
    box.style.display = "block";

    // fonction recursive permet de replacer la boite au centre
    replacement(box);

}

function replacement(boite)
{
    boite.style.marginTop = -(boite.offsetHeight / 2) + "px";
    setTimeout(function(){replacement(boite)}, 200);
}
