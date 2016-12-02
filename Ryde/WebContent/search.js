$(function(){
	$("#makeSearchbox").keypress(function(){
		var inputText = $("#makeSearchbox").val();
		//returns the value of the input area to variable
		
		$.ajax({
			url:"Searchlet",
			type:"GET",
			data:"inputText="+inputText,
			success: function(data){
				$("#makeSearchbox").val(data);
			}
		});
	})
});


