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


function getPrice()
{
    
    var startDate = $("#from").val(); 
    var endDate = $("#to").val(); 
    
    var msDay = 1000*60*60*24; 
    
    var d1 = new Date(startDate); 
    var d2 = new Date(endDate); 
    
    var totalTime = Math.abs(d1-d2); 
    //difference in milliseconds between two dates. 
    
    totalTime = Number(totalTime);
    //converts date to stirng. 
    
    totalTime = totalTime % msDay; 
	
    var totalPrice = 20 * totalTime; 
	
    $("#priceBox").val(totalPrice); 
    
    //return 20 * totalTime; 
    //calculates the price and returns. 
    
    
}
