window.onload = function() {
	// Buttons used to scroll movies
	prevgroup = document.getElementById("prevgroupbutton");
	nextgroup = document.getElementById("nextgroupbutton");
	sokestreng = document.getElementById("sokestreng");
	query_params = get_query_string_parameters();																	// gets the parameters from the URL
	filter = {};																																	// Creates / empties the filter object later used on the collection of movies
	search_results = movies_object;
	global_id = "";

		sokForTing();

}

function sokForTing(){

	if (query_params.film_title) {																								// Checks if there is a title filter in the URL
			filter.film_title = query_params.film_title.toLowerCase().replace(/[^0-9a-z ]/g,""); // adds the filter into an object, makes sure its lowercase and doesn't contain anything else than space/a-z/0-9
    }

	if (query_params.actor) {																											// Checks if there is an actor filter in the URL
			filter.actor = query_params.actor.toLowerCase().replace(/[^0-9a-z ]/g,"");// adds the filter into an object, makes sure its lowercase and doesn't contain anything else than space/a-z/0-9
    }

	if (query_params.director) {																									// Checks if there is a director filter in the URL
			filter.director = query_params.director.toLowerCase().replace(/[^0-9a-z ]/g,"");// adds the filter into an object, makes sure its lowercase and doesn't contain anything else than space/a-z/0-9
    }

	if (query_params.country) {																										// Checks if there is a country filter in the URL
			filter.country = query_params.country.toLowerCase().replace(/[^0-9a-z ]/g,"");// adds the filter into an object, makes sure its lowercase and doesn't contain anything else than space/a-z/0-9
		}

	if (query_params.genre) {																											// Checks if there is a genre filter in the URL
			filter.genre = query_params.genre.toLowerCase().replace(/[^0-9a-z ]/g,"");// adds the filter into an object, makes sure its lowercase and doesn't contain anything else than space/a-z/0-9
    }

		if (isEmpty(filter)){
						return;
				}

			filtered_result = searchWithFilter(search_results);									  // Sends all movies, and the filter object with filters created based on URL
			display(filtered_result);			// sends the movies that match the filters to a display function

}


function searchWithFilter(search_results){

	results = search_results; 																										// Stores all movies in objects.js before filter deletes what doesn't match
	genres = genres_object;
	/* for all movies in results check if there is a filter written in the URL,
	then run the filter function if it does.  If the function returns
	 true the movie is deleted from results */
	for (movie in results){
		if (filter.film_title){
			if (filterTitle(results[movie],filter.film_title)){
				delete results[movie];
			}
		}
		else if (filter.director){
			if (filterDirector(results[movie], filter.director)){
				delete results[movie];
			}
		}
		 else if (filter.country){
			if (filterCountry(results[movie], filter.country)){
				delete results[movie];
			}
		}
		else if (filter.actor){
			if (filterActor(results[movie], filter.actor)){
				delete results[movie];
			}
		}
		else if (filter.genre){
			if (filterGenre(results[movie], genres)){
				delete results[movie];
			}
		}
	}
//console.log(results);																													 // logs the results
return results; 																																 // Returns the filtered result
}

function display(films) {
	var spliced_movies = spliceArray(films);
	var currentpage = 0;																	// all movies are sent to spliceArray to be spliced into smaller arrays then stored in a big one
	var arrayLength = spliced_movies.length;
											// this is probably where it should continue
	updateThing(0, spliced_movies, films);



	nextgroup.onclick = function() {
		if (currentpage +1 < arrayLength){
			currentpage += 1;
			console.log("currentpage: "+currentpage);
			updateThing(currentpage, spliced_movies, films);
		}
	}

	prevgroup.onclick = function() {
		if (currentpage > 0){
			currentpage -= 1;
			console.log("currentpage: "+currentpage);
			updateThing(currentpage, spliced_movies, films);
		}
	}
}

function updateThing(currentpage, spliced_movies, films){
	refreshDisplay(currentpage, spliced_movies);
	updateSearchString(films,spliced_movies);
	updatePageSpan(spliced_movies);
}

function refreshDisplay(id, spliced_movies){
	global_id = id;
	var current = spliced_movies[id];
	var filmerclass = document.getElementsByClassName("filmer")[0];
	var lithing = document.createElement("li");
	while (filmerclass.firstChild) {
    filmerclass.removeChild(filmerclass.firstChild);
}
	for (movie in current){
		var _li = makeLiBlocks(current[movie]);
		filmerclass.appendChild(_li);
	}
	/*
	if (spliced_movies.length > 0){
		lithing.appendChild(spliced_movies[id]); // <-- Changes group "id"
		filmerclass.appendChild(lithing);
	}
	*/
	checkButtons(id, spliced_movies.length);
}

function checkButtons(id, length){
	// Checks if prevbutton needs to be visisble
	if (id == 0){
		prevgroupbutton.disabled = true;
	}
	else if (length <= 0){
		prevgroupbutton.disabled = true;
	} else {
		prevgroupbutton.disabled = false;
	}
	//Checks if nextbutton needs to be visible
	if (id+1 == length){
		nextgroupbutton.disabled = true;
	}	else if (length <= 0){
		nextgroupbutton.disabled = true;
	}  else {
		nextgroupbutton.disabled = false;
	}
}

function spliceArray(films) {

	arrayofblocks = [];																														// resets the arrays
	arrayofthings = [];

	for (film in films) {
		arrayofthings.push(films[film]);																						// converts the object array thing into a normal array
	}

	lengthOfArray = arrayofthings.length;																					// creates a temp var for the length of the array, as splice will remove objects as it goes.  Prevents bugs

	for (var i = 0; i < lengthOfArray/10; i++){																		// do this this many times based on how many movies there are in the search result / 10
	  x = arrayofthings.splice(0, 10);																						// splices the 10 first objects of the array
		arrayofblocks.push(x);
	}

	return arrayofblocks;

}

function makeLiBlocks(film) {
	if (film){
		var _imgsrc = getMovieImg(film.id);
		var _node = newElem("class","film","li");
		var _link = newElem("class","film_link","a");
		_link.href="film.htm?filmid="+film.id +"&imgsrc="+_imgsrc;
		// Figure > IMG / Figcaption
		var _figure = newElem(false,false,"figure");
		var _img = newElem("class","film_img","img");
		var _figcaption = newElem("class","filmtitle","figcaption");
			_img.src = "https://nelson.uib.no/o/" +_imgsrc + ".jpg";
			_img.alt = "Bilde av: " + film.otitle + ".";
		var film_title = document.createTextNode(film.otitle);
			_link.appendChild(film_title);
			_figcaption.appendChild(_link);
		_figure.appendChild(_img);
		_figure.appendChild(_figcaption);

		_node.appendChild(_figure);
		console.log(_node);
	return _node;
	}

}

/*
<ol class="filmer">
	<li>
		<figure>
			<img src="" alt=""><!--bilde?-->
			<figcaption><!--filmtittel--></figcaption>
		</figure>
	</li>
	<li>
		<figure>
			<img src="" alt=""><!--bilde?-->
			<figcaption><!--filmtittel--></figcaption>
		</figure>
	</li>
</ol>

	var div = document.createElement("div");																			// creates a DOM Div
	div.className = "movieGroup";																									// sets classname to movieGroup

	for (film in current) {																												// for each film in current(array of 10 movies)
		var movie = document.createElement("figure");																// Create a DOM Html5 figure
		movie.className = "movie"+" m"+film;																				// set its class to (universal)movie & m+0-9 (m0-9 used for fancy stuff)


		films_img = document.createElement("img");																	// Creates the img element
		films_img.className = "film_img";																						// Sets class to film_img
		films_img.src = getMovieImg(current[film].id);															// runs getMovieImg to genereat the src of the image based on movies ID
		films_img.alt = "Bilde av: " + current[film].otitle + ".";									// Sets alt to "picture of" + movies tittle
		movie.appendChild(films_img);																								// appends img(img) to movie(figure)
		div.appendChild(movie);																											// appends movie(figure) to div (div)

		films_title = document.createTextNode(current[film].otitle);								// create a textnode with movies title
		title_fig = document.createElement("figcaption");														// create a figcaption element
		title_fig.className = "filmtitle";																					// sets class of title_fig(figcaption)
		title_fig.appendChild(films_title);																					// appends films_title(textnode) to title_fig(figcaption)
		movie.appendChild(title_fig);																								// appends title_fig(figcaption) to movie(figure)

	}
	return div;  																																	// returns the div created containing 0-10 movies
	*/


function updateSearchString(films, spliced){
	var current = spliced[global_id];
	pageAmount = current.length;
	arrayedfilms = [];
	searchString ="";

	for (movie in films){
		arrayedfilms.push(films[movie]);
	}

	searchString = getFilters(filter);
	searchString += " (viser " + pageAmount + " av " + arrayedfilms.length + ")";
	sokestreng.innerHTML = searchString;

}

function getFilters(filters){
	stringen = "";
	if (filters.film_title){
		stringen += "\"" + filters.film_title + "\", ";
	}
	if (filters.director){
		stringen +=  "\"" + filters.director + "\", ";
	}
	if (filters.country){
		stringen +=  "\"" + filters.country + "\", ";
	}
	if (filters.actor){
		stringen +=  "\"" + filters.actor + "\", ";
	}
	if (filter.genre){
		stringen +=  "\"" + filters.genre + "\"";
	}

	stringen += "   ";
	return stringen;
}

function updatePageSpan(spliced){
	stringen = "Page " + (global_id+1) + " of " + spliced.length;

	span = document.getElementById("pagenumber");
	span.innerHTML = stringen;
}

// Filters array of ALL movies, removes movies that dont match filter.
//FALSE = IT MATCHES THE FILTER AND WONT BE DELETED
//TRUE = IT DOESN'T MATCH THE FILTER AND WILL BE DELETED FROM THE SEARCH ARRAY
// filters title
function filterTitle(themovie, filterr){
					if (themovie.otitle){ 																						 // if the movie has a title create a temp var and set it to lowercase
						title = themovie.otitle.toLowerCase();
					} else {
						return true;
					}

					if (title.includes(filterr)){  													// compare temp title with filter, returns false if it matches
						return false;
					} else {
						return true;
					}
}

// Filters directors
function filterDirector(themovie, filterr){
					if (themovie.dir){  																							// if the movie has a director create a temp var and set it to lowercase
						director = themovie.dir.toLowerCase();
					} else {
						return true;
					}

					if (director.includes(filterr)){  											// Compare temp director with filter, returns false if matches
						return false;
					} else {
						return true;
					}
}

// Filters country
function filterCountry(themovie, filterr){
				if (themovie.country){ 																							 //if movie has a country create temp var of country in lowercase, else return true
					land = themovie.country.toLowerCase();
				} else {
					return true;
				}

				if (land.includes(filterr)){													  // compares temp var with filter, returns false if it matches
					return false;
				} else {
					return true;
				}
}

// Filters actors
function filterActor(themovie, filterr){
				if (themovie.folk){ 																							 //if the movie has actors create a temp var in lowercase else return true
					actor = themovie.folk.toLowerCase();
				} else {
					return true;
				}

				if (actor.includes(filterr)){														  // Compares temp actor with filter (all should be lowerbase by this point)
					return false;																									// returns false on all movies that match the filter (not delete)
				} else {
					return true;
				}
}

// Filters genres
function filterGenre(themovie, genres){
				if (genres[themovie.id]){																				// checks if the genre based on movies id excists
					if ((genres[themovie.id].includes(filter.genre))){						//If the genre with movies id doesn't match the filter.genre then do..
						//console.log("not deleted");
						return false;
					} else {
						//console.log("deleted");
						return true;
					}
				} else {																													//if there are no genres matching a movie id also return false
					//console.log("no id match");
					return false;
				}
}

// Gets URL for each movie based on ID
function getMovieImg(id) {
	var pageid = Math.floor(id / 1000);																		// Always returns 0 on ids between 0-999, 1 for 1000-1999, 2 for 2000-2999 etc
	var link = pageid +"/"+ id;			// creates the link with pageid and movies id.

	return link;
}

// Utility function to check if an object is empty
function isEmpty(object) {
    for(var x in object) {		// for each thing in object check if x is in object.  if is in object that means its not empty.
        if(object.hasOwnProperty(x))
            return false;
    }
    return true;
}
// Hans sin newElem
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
