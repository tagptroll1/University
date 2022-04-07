/*
newComment("skript_test1","3","drittfilm!!!");
newComment("skript_test2","2","nice skript..");
newComment("skript_test3","3","drittfilm!!!");
newComment("skript_test4","5","verry nice film i like film much very very...");
set_ytube("http://www.imdb.com/title/tt0137523/");
add_actor("SKRIPT_TEST per åsnes");
add_actor("SKRIPT_TEST kjellaug ballesvor");
fBilde.src = "https://images-na.ssl-images-amazon.com/images/M/MV5BODg0NjQ5ODQ3OF5BMl5BanBnXkFtZTcwNjU4MjkzNA@@._V1_UY1200_CR85,0,630,1200_AL_.jpg"
fTittel.innerHTML = "thomas toget";
arsTall.innerHTML = "(2000ogtest)"
fLengde.innerHTML = "(one time)";
*/

// For å dytte stuff/text inn
//bilde            >>>  fBilde.src = bildelink;
//tittel           >>>  fTittel.innerHTML ="tittel";
//Årstall          >>>  arsTall.innerHTML ="(2005)";
//lengde på film   >>>  fLengde.innerHTML = "(1t 10min ..elns)";
//avg Rating       >>>  avgRating.innerHTML = " avg rating: 4.5 av 5"

//?? regissør >>> fRegissor. something
//?? actors >>> actorList. (do some magic here..)






//youtube trailer
//param a = youtube link
function set_ytube(a) {
  if (!a) {//no youtube link
    ytube.style.visibility ="collapse";
  } else {
    ytube.href = "https://youtu.be/"+a;
  }
}


//legg til skuespiller
function add_actor(_actor) {
  if (!_actor) {
    return false;
  } else {

    var a = newElem(false,false,"li");
    var b = document.createTextNode(_actor);
    a.appendChild(b);
    actorList.appendChild(a);
  }
  //console.log(a+"  parameter:"+_actor);
}



// lag ny kommentar param(brukernavn,rating,kommentar)
function newComment(usrN,uR,uC) {
  var _node = newElem("class","enKommentar","li");
  var _user = newElem("class","user","span");
  var _rating = newElem("class","uRating","span");
  var _comment = newElem("class","uComment","span");

  var x = document.createTextNode(usrN);
  _user.appendChild(x);

  x = document.createTextNode(" ("+uR+" av 5) ");
  _rating.appendChild(x);

  x = document.createTextNode(uC);
  _comment.appendChild(x);

  _node.appendChild(_user);
  _node.appendChild(_rating);
  _node.appendChild(_comment);

  if (!_node) {
    return false;
  } else {
    document.getElementById('uRev').appendChild(_node);
  }
  //console.log(_node);
}




/*
//(litt) raskere laging av elementer
/*(_type,_atr,_elem)--> class/id...etc , nøkkel , elementtype
  hvis _type eller _atr er null returneres bare elem.
  for <li> uten id = newElem(false,false,"li")
  span... newElem("id/class","id/class>>nøkkel","span")
*/
function newElem(_type,_atr,_elem) {
  if (_elem) {
    var el = document.createElement(_elem);
  } else {
    return;
  }

  if (_type || _atr) {
    el.setAttribute(_type,_atr);
  } else {
    return el;
  }
  return el;
}










/* favoriser , kommenter , lån*/

//klar for å lage kommentar function
nyKommentar.onclick = function (e) {
  e.preventDefault();
  console.log('ny kommentar!');
}

//favoriser knapp
fav.onclick = function () {
  console.log('favorisert!');
}

//låneknapp
lan.onclick = function () {
  console.log('lån denne filmen!');
}

// For å dytte stuff/text inn
//bilde            >>>  fBilde.src = bildelink;
//tittel           >>>  fTittel.innerHTML ="tittel";
//Årstall          >>>  arsTall.innerHTML ="(2005)";
//lengde på film   >>>  fLengde.innerHTML = "(1t 10min ..elns)";
//avg Rating       >>>  avgRating.innerHTML = " avg rating: 4.5 av 5"
/*
This is my stuff -
*/
var query_params = get_query_string_parameters();
var _review = reviews_object[query_params.filmid];
var _film = movies_object[query_params.filmid];
var _img = query_params.imgsrc;
fBilde.src = "https://nelson.uib.no/o/" + _img + ".jpg"
fTittel.innerHTML = _film.otitle;
arsTall.innerHTML = "("+ _film.year +")";
makeLength();
makeRating();
set_ytube(_film["youtube trailer id"]);
fDesc.innerHTML = "<strong>Berskrivelse:</strong> " + _film.description;
  var _director = _film.dir.replace(", ","</br>")
fRegissor.innerHTML = "<strong>Regissør: </strong> </br>"+_director;
addActors();
addComments();

// Maths to make time look nice
function makeLength(){
  var hours = Math.floor(_film.length / 60);
  var timeString = "( ";
  if (hours > 0){
    timeString += hours +"h ";
  }
  var minutes = _film.length % 60;
  if (minutes < 10 && minutes > 0){
    minutes = "0"+minutes;
  }
  if (minutes != "00"){
    timeString += minutes + "min";
  }
  timeString += " )";

  if (_film.length == 0 || _film.length == null){
    timeString = "";
  }
fLengde.innerHTML = timeString;
}

// Maths to check if there are reviews and make the rating string
function makeRating(){
  var amountOfReviews = 0;
  var totalrating = 0;
  for (rating in _review){
    totalrating += _review[rating].rating;
    amountOfReviews ++;
  }
  var roundedRating =Math.round(totalrating/amountOfReviews *10) / 10;
  var ratingString = " avg rating: " + roundedRating + " av 5";
  if (!_review){
    ratingString ="";
  }
avgRating.innerHTML = ratingString;
}
function addActors(){
  var _actors = _film.folk.split(", ")
  for (actor in _actors){
    add_actor(_actors[actor]);
  }
}

function addComments(){
  if (_review){
    for (comment in _review){
      var _comment = "No Comment.";
      if (_review[comment].comment){
        _comment = _review[comment].comment;
      }
      newComment(_review[comment].username, _review[comment].rating, _comment)
    }
  } else {
    commentSection.innerHTML = "Be the first to comment!";
  }


}
