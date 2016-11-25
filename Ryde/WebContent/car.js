var slideIndex;

$(document).ready(function(){
	$("#btn-right").click(function(){
		pic(1)
	});
	$("#btn-left").click(function(){
		pic(-1)});
	
	slideIndex = 1;
	showPic(slideIndex);
});

function pic(n){
	showPic(slideIndex += n);
}

function showPic(n){
	var i;
	var x = document.getElementsByClassName("images");
	if (n > x.length) {slideIndex = 1}
	if (n < 1) {slideIndex = x.length}
	for (i = 0; i < x.length; i++) {
		x[i].style.display = "none";
	}
	$(x[slideIndex-1]).css("display", "block");
}