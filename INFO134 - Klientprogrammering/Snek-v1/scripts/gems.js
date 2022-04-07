


/*---Header move easteregg?*/
h_title.onmouseover = function (e) {
  var xpos = e.clientX - h_title.offsetLeft;
  var xsize = h_title.offsetWidth;
  var m = (Math.random()*20);
  //console.log("musX=   "+xpos+"  størrelse=  "+xsize);
  if (xpos>xsize/2) {
    moveLogo(0-m);
  }else {
    moveLogo(m);
  }
}
h_title.onmouseout = function () {//reset
  h_title.style.MozTransform ="translateX("+(0)+"px)";
  h_title.style.transform ="translateX("+(0)+"px)";
  h_title.style.color ="inherit";
  h_title.style.transition ="1.2s all";
  h_title.style.textShadow = "0px 10px 10px black";
}
function moveLogo(m) {//move
  h_title.style.MozTransform ="translateX("+(m)+"px)";
  h_title.style.transform ="translateX("+(m)+"px)";
  h_title.style.color ="white";
  h_title.style.transition =".3s all";
  h_title.style.textShadow = 0-m+"px 10px 20px #00a8fa";
}
//   text-shadow: 0px 10px 10px black;






/*---Søk ( vis eller gjem søkefelter )*/
hideitnow();

function hideitnow() {
  search.style.visibility = "collapse";
  sok_f.style.visibility = "visible";
  sok_f.childNodes[2].style.display = "none";
  //console.log('search hidden!');
}
sok_f.onclick = function () {
  //console.log('click! adv søk');
  if (search.style.visibility !== "collapse") {//gjemmer
    sok_f.style.opacity = "1";
    search.style.visibility = "collapse";
    search.style.opacity = "0";
    sok_f.childNodes[1].style.display = "inline";
    sok_f.childNodes[2].style.display = "none";
  }else {//viser
    sok_f.style.opacity = "0.5";
    search.style.visibility = "visible";
    search.style.opacity = "1";
    sok_f.childNodes[1].style.display = "none";
    sok_f.childNodes[2].style.display = "inline";
  }
}







//sjekker for "nullsøk"
checkfields();

//eventlistener på form (søkeskjema)
search.addEventListener("keyup",function (e) {
  if (e.target.classList.contains("inpt")) {
    //console.log('checkingstuff');
    checkfields();
  }
});


//sjekker om søkefeltet er fyllt ut
function checkfields() {
  var sum =( film_title.value
    + actor.value
    + director.value
    + genre.value
    + country.value );
  var len = sum.trim().length;

      if (len>0) {
        //console.log('ENABELING');
        diablesubmitsearch(false);
      }else {
        //console.log('DISABELING');
        diablesubmitsearch(true);
      }
}

//styrer om søkeknappen skal være enabled / disabled
function diablesubmitsearch(b) {
  if (b) {
    //disable (bad search)
    submit_search.setAttribute("disabled","disabled");
  } else {
    //enable (good search)
    submit_search.removeAttribute("disabled");
  }
}




















/*
const = bare en konstant ..
var = en variabel som tas i bruk når den trengs.
_   = (ingenting) en global var?
*/



//speiler logo (header)
/*
window.onload = function () {
  var h_title = document.getElementById('h_title').getElementsByTagName('span')[0];
  var mirr = h_title.cloneNode(true);

  document.getElementById('h_title').appendChild(mirr);
}
*/
