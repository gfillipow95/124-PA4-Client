
//Scroll down homepage when navbar link is clicked
var aboutBtn = document.querySelector("#navbar-about");
var makerBtn = document.querySelector("#navbar-makers");
var mugBtn = document.querySelector("#navbar-mugs");
var coffeeBtn = document.querySelector("#navbar-coffee");
var homeBtn = document.querySelector("#navbar-home");


aboutBtn.addEventListener("click", function(e){
    document.querySelector("#about").scrollIntoView();
});

makerBtn.addEventListener("click", function(e){
    document.querySelector("#makers").scrollIntoView();
});

mugBtn.addEventListener("click", function(e){
    document.querySelector("#mugs").scrollIntoView();
});

coffeeBtn.addEventListener("click", function(e){
    document.querySelector("#coffee").scrollIntoView();
});

homeBtn.addEventListener("click", function(e){
    document.querySelector("#home").scrollIntoView();
});
