/**
 * This javascript file is responsible for
 * functionalities on the cars.ftl page.
 * Specifically, the image slide show.
 */

var slideIndex;

$(document).ready(function(){
	$("#btn-right").click(function(){
		pic(1)
	});
	$("#btn-left").click(function(){
		pic(-1)});
	
	showPic(0);
	
	slideIndex = 1;
	showPic(slideIndex);
	
});

function pic(n){
	$('#current').parent().trigger('zoom.destroy');
	$('#current').unwrap();
	showPic(slideIndex += n);
}

function showPic(n){
	var i;
	var x = document.getElementsByClassName("images");
	if (n > x.length) {slideIndex = 1}
	if (n < 1) {slideIndex = x.length}
	for (i = 0; i < x.length; i++) {
		$(x[i]).css("display", "none");
		$(x[i]).removeAttr('id');
	}
	$(x[slideIndex-1]).css("display", "block");
	$(x[slideIndex-1]).attr("id", "current");
	$("#current")
		.wrap('<span style="display:inline-block"></span>')
	    .css('display', 'block')
	    .parent()
	    .zoom();
}